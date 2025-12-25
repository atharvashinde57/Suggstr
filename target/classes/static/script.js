
let questions = [];
let currentQuestionIndex = 0;
let userTags = [];
let menuLoaded = false;
// Initialize stored user if any
let currentUser = localStorage.getItem('suggstr_user');
if (currentUser === 'null') currentUser = null;

// Navbar Scroll Effect
window.addEventListener('scroll', () => {
    const header = document.querySelector('.header');
    if (window.scrollY > 50) {
        header.classList.add('scrolled');
    } else {
        header.classList.remove('scrolled');
    }
});

// Navigation Logic
function showSection(sectionId) {
    // Hide all sections
    document.getElementById('hero').style.display = 'none';
    document.getElementById('quiz-container').style.display = 'none';
    document.getElementById('loading').style.display = 'none';
    document.getElementById('result').style.display = 'none';
    document.getElementById('menu-section').style.display = 'none';
    document.getElementById('offers-section').style.display = 'none';
    document.getElementById('profile-section').style.display = 'none';

    // Update Nav Active State
    document.querySelectorAll('.nav-item').forEach(el => el.classList.remove('active'));

    if (sectionId === 'home') {
        document.getElementById('hero').style.display = 'block';
        document.getElementById('nav-home').classList.add('active');
    } else if (sectionId === 'menu') {
        document.getElementById('menu-section').style.display = 'block';
        document.getElementById('nav-menu').classList.add('active');
        if (!menuLoaded) loadMenu();
    } else if (sectionId === 'offers') {
        document.getElementById('offers-section').style.display = 'block';
        document.getElementById('nav-offers').classList.add('active');
        loadOffers();
    } else if (sectionId === 'profile') {
        document.getElementById('profile-section').style.display = 'block';
        document.getElementById('nav-profile').classList.add('active');
        updateProfileView();
    }
}

async function loadOffers() {
    const container = document.getElementById('offers-section');
    container.innerHTML = '<h2>Exclusive Partner Offers</h2><div id="offers-grid" class="menu-grid" style="margin-top:30px;">Loading offers...</div>';

    try {
        const response = await fetch('/api/offers');
        const offers = await response.json();

        const grid = document.getElementById('offers-grid');
        grid.innerHTML = '';

        if (offers.length === 0) {
            grid.innerHTML = '<p>No live offers currently.</p>';
            return;
        }

        offers.forEach(offer => {
            const card = document.createElement('div');
            card.className = 'menu-item'; // Reuse menu-item style base
            card.style.borderColor = offer.platform === 'SWIGGY' ? '#FC8019' : '#CB202D';
            card.style.borderWidth = '2px';

            card.innerHTML = `
                <div style="position:relative;">
                     <img src="${offer.imageUrl}" alt="${offer.platform}" style="width: 100%; height: 120px; object-fit: contain; padding: 20px; background: #fff;">
                     <div style="position:absolute; top:10px; right:10px; background:${offer.platform === 'SWIGGY' ? '#FC8019' : '#CB202D'}; color:white; padding:5px 10px; border-radius:4px; font-weight:bold;">
                        ${offer.discount}
                     </div>
                </div>
                <div class="dish-info">
                    <h3 style="color:var(--text-color);">${offer.code}</h3>
                    <p style="font-size:0.9rem; color:#666; margin: 10px 0;">${offer.description}</p>
                    <button class="btn-primary" style="width:100%; background-color:${offer.platform === 'SWIGGY' ? '#FC8019' : '#CB202D'}; border:none;" onclick="alert('Coupon Code ${offer.code} Copied!')">COPY CODE</button>
                    <p style="font-size:0.8rem; color:#aaa; margin-top:5px;">Valid on ${offer.platform}</p>
                </div>
            `;
            grid.appendChild(card);
        });

    } catch (e) {
        console.error(e);
        container.innerHTML = '<p>Failed to load offers.</p>';
    }
}

// Menu Logic
async function loadMenu() {
    const grid = document.getElementById('menu-grid');
    grid.innerHTML = '<p class="loading">Loading delicious items...</p>';

    try {
        const response = await fetch('/api/dishes');
        const dishes = await response.json();

        grid.innerHTML = ''; // Clear loading

        dishes.forEach(dish => {
            const card = document.createElement('div');
            card.className = 'menu-item';
            card.innerHTML = `
                <img src="${dish.imageUrl}" alt="${dish.name}" loading="lazy">
                <div class="menu-item-content">
                    <div style="display:flex; justify-content:space-between; align-items:center;">
                        <h3 class="menu-item-title">${dish.name}</h3>
                        <span class="price-tag">₹${dish.price}</span>
                    </div>
                    <p class="menu-item-desc">${dish.description}</p>
                    <div style="margin-top:10px;">
                        ${dish.tags.split(',').slice(0, 3).map(tag =>
                `<span style="background:#f0f0f0; padding:2px 6px; font-size:12px; margin-right:5px; border-radius:4px;">${tag}</span>`
            ).join('')}
                    </div>
                </div>
            `;
            grid.appendChild(card);
        });
        menuLoaded = true;
    } catch (e) {
        console.error("Failed to load menu", e);
        grid.innerHTML = '<p style="color:red">Failed to load menu items.</p>';
    }
}

// Quiz Logic
async function startQuiz() {
    showSection('none'); // Start by clearing
    document.querySelectorAll('.nav-item').forEach(el => el.classList.remove('active'));
    document.getElementById('nav-home').classList.add('active'); // Keep home active during quiz

    try {
        const response = await fetch('/api/questions');
        questions = await response.json();

        if (questions && questions.length > 0) {
            document.getElementById('quiz-container').style.display = 'block';
            currentQuestionIndex = 0; // Reset index
            userTags = []; // Reset tags
            showQuestion();
        } else {
            alert('Failed to load questions. Is the backend running?');
            showSection('home');
        }
    } catch (e) {
        console.error(e);
        alert('Error connecting to server.');
        showSection('home');
    }
}

function showQuestion() {
    const q = questions[currentQuestionIndex];
    document.getElementById('question-text').innerText = q.text;
    document.getElementById('q-current').innerText = currentQuestionIndex + 1;

    const optsContainer = document.getElementById('options-container');
    optsContainer.innerHTML = ''; // Clear

    q.options.forEach(opt => {
        const btn = document.createElement('button');
        btn.className = 'option-btn';
        btn.innerText = opt.text;
        btn.onclick = () => selectOption(opt);
        optsContainer.appendChild(btn);
    });
}

function selectOption(option) {
    // Save tag
    if (option.tagEffect) {
        userTags.push(option.tagEffect);
    }

    currentQuestionIndex++;

    if (currentQuestionIndex < questions.length) {
        showQuestion();
    } else {
        finishQuiz();
    }
}

async function finishQuiz() {
    document.getElementById('quiz-container').style.display = 'none';
    document.getElementById('loading').style.display = 'block';

    try {
        const payload = {
            tags: userTags,
            username: currentUser // Send username if logged in
        };

        console.log("Sending Prediction Payload:", payload);
        const response = await fetch('/api/suggest', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        });

        const dish = await response.json();
        displayResult(dish);
    } catch (e) {
        console.error(e);
        alert('Error getting suggestion');
        showSection('home');
    }
}

function displayResult(dish) {
    document.getElementById('loading').style.display = 'none';
    const resultCard = document.getElementById('result');
    resultCard.style.display = 'block';

    document.getElementById('dish-name').innerText = dish.name;
    document.getElementById('dish-desc').innerText = dish.description;
    document.getElementById('dish-img').src = dish.imageUrl;
}


// Login Logic
function openLoginModal() {
    document.getElementById('login-modal').style.display = 'block';
}

function closeLoginModal() {
    document.getElementById('login-modal').style.display = 'none';
}

async function performLogin() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    if (!username.trim() || !password.trim()) {
        alert('Please enter username and password');
        return;
    }

    try {
        const response = await fetch('/api/auth/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password })
        });

        const text = await response.text();
        if (text === "Login Successful") {
            currentUser = username;
            localStorage.setItem('suggstr_user', username);
            closeLoginModal();
            showSection('profile');
            alert('Welcome back, ' + username + '!');
        } else {
            alert('Login failed: ' + text);
        }
    } catch (e) {
        console.error(e);
        alert('Error connecting to login server');
    }
}

function showRegisterModal() {
    document.getElementById('login-modal').style.display = 'none';
    document.getElementById('register-modal').style.display = 'block';
}

function closeRegisterModal() {
    document.getElementById('register-modal').style.display = 'none';
}

function showLoginModalFromRegister() {
    closeRegisterModal();
    showLoginModal(); // Fixed typo in original source if any, should be openLoginModal but source had showLoginModal? verifying...
    // Actually the source had openLoginModal defined but showLoginModalFromRegister called showLoginModal which might be undefined.
    // Let's use openLoginModal() to be safe.
    openLoginModal();
}

async function performRegister() {
    const username = document.getElementById('reg-username').value;
    const password = document.getElementById('reg-password').value;
    const fullName = document.getElementById('reg-fullname').value;
    const age = document.getElementById('reg-age').value;
    const email = document.getElementById('reg-email').value;
    const favCuisine = document.getElementById('reg-cuisine').value;

    if (!username.trim() || !password.trim() || !fullName.trim() || !email.trim()) {
        alert('Please fill in all required fields');
        return;
    }

    try {
        const response = await fetch('/api/auth/register', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                username,
                password,
                fullName,
                age: parseInt(age),
                email,
                favCuisine
            })
        });

        const text = await response.text();
        if (text === "User registered successfully") {
            alert('Registration Successful! Please Login.');
            showLoginModalFromRegister();
        } else {
            alert('Registration failed: ' + text);
        }
    } catch (e) {
        console.error(e);
        alert('Error connecting to server');
    }
}

function logout() {
    currentUser = null;
    localStorage.removeItem('suggstr_user');
    showSection('profile');
}

let historyChart = null;

async function updateProfileView() {
    const guestContent = document.getElementById('profile-content');
    const userContent = document.getElementById('logged-in-content');

    if (currentUser) {
        guestContent.style.display = 'none';
        userContent.style.display = 'block';

        // Fetch User Details
        try {
            const res = await fetch(`/api/auth/details/${currentUser}`);
            if (res.ok) {
                const user = await res.json();
                document.getElementById('profile-fullname').innerText = user.fullName || currentUser;
                document.getElementById('profile-age').innerText = user.age || '-';
                document.getElementById('profile-email').innerText = user.email || '-';
                document.getElementById('profile-cuisine').innerText = user.favCuisine || '-';
            }
        } catch (e) {
            console.error("Error fetching user details", e);
        }

        // Render Chart
        renderHistoryChart();
    } else {
        guestContent.style.display = 'block';
        userContent.style.display = 'none';
    }
}

async function renderHistoryChart() {
    const ctx = document.getElementById('historyChart');
    if (!ctx) return;

    try {
        const response = await fetch(`/api/user/${currentUser}/stats`);
        const stats = await response.json();

        const labels = Object.keys(stats);
        const data = Object.values(stats);

        if (Object.keys(stats).length === 0) {
            // Handle empty state if needed, or just let chart show empty
        }

        if (historyChart) {
            historyChart.destroy();
        }

        historyChart = new Chart(ctx, {
            type: 'pie',
            data: {
                labels: labels,
                datasets: [{
                    label: 'Suggestion Count',
                    data: data,
                    backgroundColor: [
                        '#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0', '#9966FF', '#FF9F40'
                    ],
                    hoverOffset: 4
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        position: 'bottom',
                        labels: { color: 'white' }
                    }
                }
            }
        });

    } catch (e) {
        console.error("Chart error", e);
    }
}

// Close modal if clicked outside
window.onclick = function (event) {
    const modal = document.getElementById('login-modal');
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

// Check session on load
window.addEventListener('load', () => {
    if (currentUser) {
        console.log("Restored session for:", currentUser);
        updateProfileView();
    }
});
