
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
    container.innerHTML = '<h2>Exclusive Partner Offers</h2><div id="offers-grid" class="menu-grid" style="margin-top:30px;"></div>';

    // STATIC OFFERS
    const offers = [
        { platform: 'SWIGGY', code: 'SWIGGY50', description: 'Get 50% OFF on your first order', discount: '50% OFF', imageUrl: 'https://upload.wikimedia.org/wikipedia/en/1/12/Swiggy_logo.svg' },
        { platform: 'ZOMATO', code: 'TRYNEW', description: 'Flat ₹100 OFF on orders above ₹199', discount: '₹100 OFF', imageUrl: 'https://upload.wikimedia.org/wikipedia/commons/b/bd/Zomato_Logo.svg' },
        { platform: 'SWIGGY', code: 'JUMBO', description: 'Free Delivery on Jumbo Packs', discount: 'FREE DEL', imageUrl: 'https://upload.wikimedia.org/wikipedia/en/1/12/Swiggy_logo.svg' },
        { platform: 'ZOMATO', code: 'ZOMATO30', description: '30% OFF up to ₹75', discount: '30% OFF', imageUrl: 'https://upload.wikimedia.org/wikipedia/commons/b/bd/Zomato_Logo.svg' }
    ];

    const grid = document.getElementById('offers-grid');
    offers.forEach(offer => {
        const card = document.createElement('div');
        card.className = 'menu-item';
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
}

// Menu Logic
async function loadMenu() {
    const grid = document.getElementById('menu-grid');
    grid.innerHTML = '<p class="loading">Loading delicious items...</p>';

    try {
        // STATIC FETCH
        const response = await fetch('data/dishes.json');
        const dishes = await response.json();

        // Sort Alphabetically cause we promised
        dishes.sort((a, b) => a.name.localeCompare(b.name));

        grid.innerHTML = '';

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
    showSection('none');
    document.querySelectorAll('.nav-item').forEach(el => el.classList.remove('active'));
    document.getElementById('nav-home').classList.add('active');

    try {
        // STATIC FETCH
        const response = await fetch('data/questions.json');
        questions = await response.json();

        if (questions && questions.length > 0) {
            document.getElementById('quiz-container').style.display = 'block';
            currentQuestionIndex = 0;
            userTags = [];
            showQuestion();
        } else {
            alert('Failed to load questions.');
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
    optsContainer.innerHTML = '';

    q.options.forEach(opt => {
        const btn = document.createElement('button');
        btn.className = 'option-btn';
        btn.innerText = opt.text;
        btn.onclick = () => selectOption(opt);
        optsContainer.appendChild(btn);
    });
}

function selectOption(option) {
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
        // STATIC MATCHING LOGIC
        const response = await fetch('data/dishes.json');
        const allDishes = await response.json();

        let bestMatch = null;
        let maxScore = 0;

        allDishes.forEach(dish => {
            let score = 0;
            const dishTags = dish.tags.split(',');
            userTags.forEach(uTag => {
                dishTags.forEach(dTag => {
                    if (dTag.trim().toUpperCase() === uTag.trim().toUpperCase()) score++;
                });
            });
            if (score > maxScore) {
                maxScore = score;
                bestMatch = dish;
            }
        });

        // Fallback random
        if (!bestMatch) {
            bestMatch = allDishes[Math.floor(Math.random() * allDishes.length)];
        }

        // Simulate Delay
        setTimeout(() => {
            displayResult(bestMatch);
        }, 1500);

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

    // STATIC LOGIN MOCK
    currentUser = username;
    localStorage.setItem('suggstr_user', username);
    // Mock user details storage
    localStorage.setItem('suggstr_details_' + username, JSON.stringify({
        fullName: 'Demo User',
        age: 25,
        email: username + '@example.com',
        favCuisine: 'North Indian'
    }));

    closeLoginModal();
    showSection('profile');
    alert('Welcome back, ' + username + '! (Demo Mode)');
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
    openLoginModal();
}

async function performRegister() {
    const username = document.getElementById('reg-username').value;
    const fullName = document.getElementById('reg-fullname').value;
    const age = document.getElementById('reg-age').value;
    const email = document.getElementById('reg-email').value;
    const favCuisine = document.getElementById('reg-cuisine').value;

    if (!username.trim() || !fullName.trim()) {
        alert('Please fill in all required fields');
        return;
    }

    // STATIC REGISTER MOCK
    localStorage.setItem('suggstr_details_' + username, JSON.stringify({
        fullName, age, email, favCuisine
    }));

    alert('Registration Successful! (Demo Mode)');
    showLoginModalFromRegister();
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

        // Fetch Mock Details
        const stored = localStorage.getItem('suggstr_details_' + currentUser);
        const user = stored ? JSON.parse(stored) : { fullName: currentUser };

        document.getElementById('profile-fullname').innerText = user.fullName || currentUser;
        document.getElementById('profile-age').innerText = user.age || '-';
        document.getElementById('profile-email').innerText = user.email || '-';
        document.getElementById('profile-cuisine').innerText = user.favCuisine || '-';

        // Render Chart (Mock)
        renderHistoryChart();
    } else {
        guestContent.style.display = 'block';
        userContent.style.display = 'none';
    }
}

async function renderHistoryChart() {
    const ctx = document.getElementById('historyChart');
    if (!ctx) return;

    // Static Data
    const labels = ['North Indian', 'South Indian', 'Dessert'];
    const data = [10, 5, 2];

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
                    labels: { color: 'black' } // Fixed color for visibility on white
                }
            }
        }
    });
}

window.onclick = function (event) {
    const modal = document.getElementById('login-modal');
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

window.addEventListener('load', () => {
    if (currentUser) {
        updateProfileView();
    }
});
