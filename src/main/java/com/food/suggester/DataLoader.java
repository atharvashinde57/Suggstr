package com.food.suggester;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader {

        @Bean
        CommandLineRunner initDatabase(DishRepository dishRepository, QuestionRepository questionRepository,
                        UserRepository userRepository, OfferRepository offerRepository) {
                return args -> {
                        // Seed User
                        if (userRepository.count() == 0) {
                                userRepository.save(new User("admin", "admin123"));
                        }

                        // Reset Dishes to ensure clean list of 40 items
                        dishRepository.deleteAll();

                        // --- Existing Dishes ---
                        dishRepository.save(new Dish(null, "Alu Vadi",
                                        "Steamed colocasia leaves with spices, fried for crispiness.",
                                        "/images/aluvadi.jpg", true, 60.0, "CRISPY,SNACK,VEG,SWEET,SPICY"));
                        dishRepository.save(new Dish(null, "Bhakarwadi",
                                        "Deep fried crispy rolls filled with sweet and spicy mix.",
                                        "/images/bhakarvadi.jpg", true, 80.0, "CRISPY,SNACK,SPICY,SWEET,DRY"));
                        dishRepository.save(new Dish(null, "Egg Bhurji Pav",
                                        "Spicy scrambled eggs with buttered bread.", "/images/bhurjipav.jpg", true,
                                        110.0, "NONVEG,SPICY,DINNER,PROTEIN,HOT"));
                        dishRepository.save(new Dish(null, "Dabeli",
                                        "Spicy potato mash in bun with pomegranate and peanuts.", "/images/dabeli.jpg",
                                        true, 30.0, "SPICY,SWEET,STREET,SNACK,CHEAP"));
                        dishRepository.save(new Dish(null, "Dhokla",
                                        "Steamed spongy savory cake made from fermented batter.", "/images/dhokla.jpg",
                                        true, 50.0, "LIGHT,BREAKFAST,VEG,SNACK,GUJARATI"));
                        dishRepository.save(new Dish(null, "Masala Dosa",
                                        "Crispy rice crepe filled with spiced potatoes.", "/images/dhosa.jpg", false,
                                        90.0, "CRISPY,SOUTH,LIGHT,BREAKFAST,VEG"));
                        dishRepository.save(new Dish(null, "Misal Pav",
                                        "Spicy sprouted lentil curry topped with farsan.", "/images/misalpav.jpg", true,
                                        120.0, "SPICY,STREET,BREAKFAST,HOT,PUNJABI"));
                        dishRepository.save(new Dish(null, "Steamed Momos",
                                        "Tibetan dumplings served with spicy schezwan chutney.", "/images/momos.jpg",
                                        true, 100.0, "STREET,SNACK,LIGHT,SPICY,HOT"));
                        dishRepository.save(new Dish(null, "Omelette Pav",
                                        "Spicy masala omelette served with soft pav.", "/images/omletpav.jpg", true,
                                        60.0, "NONVEG,BREAKFAST,PROTEIN,STREET"));
                        dishRepository.save(new Dish(null, "Pani Puri",
                                        "Crispy hollow balls filled with spicy tangy water.", "/images/panipuri.jpg",
                                        true, 40.0, "SPICY,STREET,LIGHT,SNACK,COLD"));
                        dishRepository.save(new Dish(null, "Aloo Paratha",
                                        "Whole wheat flatbread stuffed with spiced potatoes.", "/images/paratha.jpg",
                                        false, 80.0, "HEAVY,BREAKFAST,NORTH,PUNJABI,HOT"));
                        dishRepository.save(new Dish(null, "Pav Bhaji",
                                        "Spiced vegetable mash served with buttery rolls.", "/images/pavbhaji.jpg",
                                        true, 150.0, "SPICY,STREET,DINNER,HOT,BUTTERY"));
                        dishRepository.save(new Dish(null, "Kanda Poha",
                                        "Flattened rice cooked with onions and turmeric.", "/images/poha.jpg", false,
                                        35.0, "LIGHT,BREAKFAST,VEG,SAVORY,HOT"));
                        dishRepository.save(new Dish(null, "Sabudana Vada",
                                        "Crispy sago patties deep fried to golden perfection.",
                                        "/images/sabudanavada.jpg", true, 55.0, "CRISPY,FASTING,SNACK,FRIED,HOT"));
                        dishRepository.save(new Dish(null, "SPDP (Sev Puri)",
                                        "Crispy flat puris topped with potato, chutneys and sev.",
                                        "/images/shevpuri.jpg", true, 60.0, "SWEET,SOUR,STREET,SNACK,COLD"));
                        dishRepository.save(new Dish(null, "Vada Pav",
                                        "The Indian burger - spicy potato fritter in a bun.", "/images/vadapav.jpg",
                                        true, 25.0, "SPICY,STREET,SNACK,HOT,CHEAP"));
                        dishRepository.save(new Dish(null, "Amruttulya Chai",
                                        "Strong, sweet, boiled tea infused with cardamom.",
                                        "/images/amruttulyachai.png", true, 15.0, "HOT,DRINK,CHEAP,SWEET,MORNING"));
                        dishRepository.save(new Dish(null, "Bun Maska",
                                        "Fresh bun slathered with generous amounts of butter.", "/images/bunmaska.png",
                                        true, 30.0, "BUTTERY,SNACK,BREAKFAST,LIGHT,CHEAP"));
                        dishRepository.save(new Dish(null, "Kanda Bhaji",
                                        "Crispy onion fritters, perfect with evening tea.", "/images/kandabhaji.png",
                                        true, 45.0, "CRISPY,FRIED,SNACK,HOT,MONSOON"));
                        dishRepository.save(new Dish(null, "Mango Mastani",
                                        "Rich mango milkshake with ice cream and dry fruits.",
                                        "/images/mangomastani.png", false, 180.0, "SWEET,DESSERT,COLD,HEAVY,FRUITY"));
                        dishRepository.save(new Dish(null, "Pithla Bhakri",
                                        "Rustic gram flour curry served with millet flatbread.",
                                        "/images/pithlabhakri.png", false, 140.0, "VILLAGE,LUNCH,VEG,HEALTHY,SPICY"));
                        dishRepository.save(new Dish(null, "Sabudana Khichdi",
                                        "Tapioca pearls tossed with peanuts and chilies.",
                                        "/images/sabudanakhichdi.png", false, 60.0,
                                        "FASTING,BREAKFAST,LIGHT,NUTTY,HOT"));
                        dishRepository.save(new Dish(null, "Tawa Pulao", "Spicy stir-fried rice with vegetables.",
                                        "/images/tawapulao.png", true, 130.0, "SPICY,STREET,DINNER,HOT,RICE"));
                        dishRepository.save(new Dish(null, "Puran Poli",
                                        "Sweet flatbread stuffed with lentil and jaggery filling.",
                                        "https://upload.wikimedia.org/wikipedia/commons/5/5d/Puran_Poli.jpg", false,
                                        50.0, "SWEET,FESTIVE,LUNCH,HOT,GHEE"));

                        // --- New Maharashtrian Beverages ---
                        dishRepository.save(new Dish(null, "Sol Kadhi",
                                        "Refreshing pink drink made from kokum and coconut milk.",
                                        "/images/solkadhi.jpg",
                                        false, 40.0, "DRINK,MARATHI,COLD,SOUR,HEALTHY"));
                        dishRepository.save(new Dish(null, "Kokum Sharbat",
                                        "Sweet and tangy coolant drink made from garcinia.", "/images/kokum.jpg",
                                        false, 30.0, "DRINK,MARATHI,COLD,SWEET,REFRESHING"));
                        dishRepository.save(new Dish(null, "Piyush",
                                        "Thick, creamy yogurt-based dessert drink.", "/images/piyush.jpg",
                                        false, 50.0, "DRINK,MARATHI,SWEET,DESSERT,COLD"));
                        dishRepository.save(new Dish(null, "Mattha",
                                        "Spiced buttermilk with fresh coriander and ginger.", "/images/mattha.jpg",
                                        false, 30.0, "DRINK,MARATHI,SAVORY,HEALTHY,COLD"));
                        dishRepository.save(new Dish(null, "Kairi Panha",
                                        "Traditional raw mango drink for summer heat.", "/images/panha.jpg",
                                        false, 40.0, "DRINK,MARATHI,SWEET,MANGO,COLD"));

                        // --- New Maharashtrian Food ---
                        dishRepository.save(new Dish(null, "Thalipeeth",
                                        "Nutritious multi-grain pancake served with butter.", "/images/thalipeeth.jpg",
                                        false, 70.0, "MARATHI,BREAKFAST,HEALTHY,SPICY,HOT"));
                        dishRepository.save(new Dish(null, "Zunka Bhakar",
                                        "Spicy gram flour porridge with sorghum bread.", "/images/zunkabhakar.jpg",
                                        false, 90.0, "MARATHI,LUNCH,VILLAGE,SPICY,VEG"));
                        dishRepository.save(new Dish(null, "Bharli Vangi",
                                        "Baby eggplants stuffed with spicy masala.", "/images/bharlivangi.jpg",
                                        false, 100.0, "MARATHI,DINNER,VEG,SPICY,HOT"));
                        dishRepository.save(new Dish(null, "Kothimbir Vadi",
                                        "Steam and fried coriander chickpea cakes.", "/images/kothimbirvadi.jpg",
                                        true, 60.0, "MARATHI,SNACK,CRISPY,FRIED,STARTER"));
                        dishRepository.save(new Dish(null, "Modak",
                                        "Sweet rice dumplings stuffed with coconut and jaggery.", "/images/modak.jpg",
                                        false, 50.0, "MARATHI,SWEET,DESSERT,FESTIVE,STEAMED"));
                        dishRepository.save(new Dish(null, "Shrikhand Puri",
                                        "Sweet saffron yogurt served with hot puffy puris.", "/images/shrikhand.jpg",
                                        false, 120.0, "MARATHI,SWEET,LUNCH,FESTIVE,HEAVY"));
                        dishRepository.save(new Dish(null, "Kolhapuri Chicken",
                                        "Spicy chicken curry with bold Kolhapuri masala.", "/images/rassa.jpg",
                                        false, 180.0, "MARATHI,NONVEG,SPICY,DINNER,CURRY"));
                        dishRepository.save(new Dish(null, "Sheera",
                                        "Traditional semolina pudding with nuts and ghee.", "/images/sheera.jpg",
                                        false, 40.0, "MARATHI,SWEET,BREAKFAST,HOT,DESSERT"));
                        dishRepository.save(new Dish(null, "Usal Pav",
                                        "Spicy mixed sprout curry served with bread.", "/images/usal.jpg",
                                        true, 70.0, "MARATHI,SPICY,LUNCH,STREET,HOT"));
                        dishRepository.save(new Dish(null, "Ragda Pattice",
                                        "Potato patties topped with dried white pea curry.", "/images/ragda.jpg",
                                        true, 60.0, "STREET,SNACK,HOT,SPICY,TANGY"));

                        // --- New South Indian Dishes ---
                        dishRepository.save(new Dish(null, "Idli Sambar",
                                        "Steamed rice cakes served with lentil soup and chutney.", "/images/idli.jpg",
                                        false, 50.0, "SOUTH,BREAKFAST,HEALTHY,VEG,STEAMED"));
                        dishRepository.save(new Dish(null, "Medu Vada",
                                        "Crispy doughnut-shaped lentil fritters.", "/images/meduvada.jpg",
                                        true, 60.0, "SOUTH,BREAKFAST,CRISPY,FRIED,SAVORY"));
                        dishRepository.save(new Dish(null, "Mysore Masala Dosa",
                                        "Crispy crepe spread with spicy red chutney and potato.",
                                        "/images/mysore_dosa.jpg",
                                        false, 110.0, "SOUTH,BREAKFAST,SPICY,CRISPY,VEG"));
                        dishRepository.save(new Dish(null, "Rava Dosa",
                                        "Thin crispy semolina crepe with onions and chillies.", "/images/rava_dosa.jpg",
                                        false, 100.0, "SOUTH,BREAKFAST,CRISPY,LIGHT,VEG"));
                        dishRepository.save(new Dish(null, "Onion Uttapam",
                                        "Thick pancake topped with caramelized onions.", "/images/uttapam.jpg",
                                        false, 90.0, "SOUTH,BREAKFAST,SOFT,VEG,SAVORY"));
                        dishRepository.save(new Dish(null, "Pesarattu",
                                        "Green moong dal crepe served with ginger chutney.", "/images/pesarattu.jpg",
                                        false, 100.0, "SOUTH,BREAKFAST,HEALTHY,PROTEIN,VEG"));
                        dishRepository.save(new Dish(null, "Appam with Stew",
                                        "Bowl-shaped rice pancake with creamy coconut veg stew.", "/images/appam.jpg",
                                        false, 140.0, "SOUTH,DINNER,CREAMY,MILD,KERALA"));
                        dishRepository.save(new Dish(null, "Idiyappam",
                                        "Steamed rice noodles served with coconut milk.", "/images/idiyappam.jpg",
                                        false, 80.0, "SOUTH,BREAKFAST,STEAMED,LIGHT,SWEET"));
                        dishRepository.save(new Dish(null, "Puttu and Kadala",
                                        "Steamed rice logs with spicy black chickpea curry.", "/images/puttu.jpg",
                                        false, 120.0, "SOUTH,BREAKFAST,KERALA,SPICY,HEAVY"));
                        dishRepository.save(new Dish(null, "Hyderabadi Biryani",
                                        "Aromatic basmati rice cooked with spicy marinated chicken.",
                                        "/images/hyd_biryani.jpg",
                                        false, 250.0, "SOUTH,DINNER,SPICY,NONVEG,RICE"));
                        dishRepository.save(new Dish(null, "Bisibelebath",
                                        "Spicy rice and lentil mash with tamarind and vegetables.",
                                        "/images/bisibelebath.jpg",
                                        false, 90.0, "SOUTH,LUNCH,SPICY,HOT,RICE"));
                        dishRepository.save(new Dish(null, "Curd Rice",
                                        "Cooling yogurt rice tempered with mustard and curry leaves.",
                                        "/images/curd_rice.jpg",
                                        false, 70.0, "SOUTH,LUNCH,COLD,LIGHT,HEALTHY"));
                        dishRepository.save(new Dish(null, "Lemon Rice",
                                        "Tangy turmeric rice with crunchy peanuts.", "/images/lemon_rice.jpg",
                                        false, 70.0, "SOUTH,LUNCH,TANGY,LIGHT,VEG"));
                        dishRepository.save(new Dish(null, "Puliyogare",
                                        "Spicy tamarind rice with roasted spices.", "/images/puliyogare.jpg",
                                        false, 80.0, "SOUTH,LUNCH,TANGY,SPICY,RICE"));
                        dishRepository.save(new Dish(null, "Chicken Chettinad",
                                        "Fiery stir-fried chicken with roasted coconut and spices.",
                                        "/images/chettinad.jpg",
                                        false, 220.0, "SOUTH,DINNER,SPICY,NONVEG,HOT"));
                        dishRepository.save(new Dish(null, "Mangalorean Fish Curry",
                                        "Tangy and spicy fish curry with coconut milk.", "/images/fish_curry.jpg",
                                        false, 280.0, "SOUTH,DINNER,SPICY,NONVEG,CURRY"));
                        dishRepository.save(new Dish(null, "Kerala Parotta",
                                        "Flaky layered flatbread served with vegetable kurma.", "/images/parotta.jpg",
                                        false, 100.0, "SOUTH,DINNER,HEAVY,FLAKY,VEG"));
                        dishRepository.save(new Dish(null, "Ven Pongal",
                                        "Buttery rice and lentil porridge seasoned with black pepper.",
                                        "/images/pongal.jpg",
                                        false, 80.0, "SOUTH,BREAKFAST,COMFORT,VEG,MILD"));
                        dishRepository.save(new Dish(null, "Payasam",
                                        "Sweet vermicelli pudding with milk and cardamom.", "/images/payasam.jpg",
                                        false, 60.0, "SOUTH,DESSERT,SWEET,HOT,FESTIVE"));
                        dishRepository.save(new Dish(null, "Rasam",
                                        "Spicy tangy tomato soup with black pepper and cumin.", "/images/rasam.jpg",
                                        false, 40.0, "SOUTH,APPETIZER,SPICY,HOT,SOUP"));
                        dishRepository.save(new Dish(null, "Dadpe Pohe",
                                        "Raw soft flattened rice tempered with mustard seeds.", "/images/dadpepohe.jpg",
                                        false, 40.0, "MARATHI,SNACK,LIGHT,RAW,VEG"));

                        // --- Seed Simulated Offers ---
                        System.out.println("DEBUG: Seeding Simulated Offers... Starting");
                        offerRepository.deleteAll();
                        System.out.println("DEBUG: Deleted existing offers");
                        offerRepository.save(new Offer("SWIGGY", "SWIGGY50", "Get 50% OFF on your first order",
                                        "50% OFF",
                                        "https://upload.wikimedia.org/wikipedia/en/1/12/Swiggy_logo.svg"));
                        offerRepository.save(new Offer("ZOMATO", "TRYNEW", "Flat ₹100 OFF on orders above ₹199",
                                        "₹100 OFF",
                                        "https://upload.wikimedia.org/wikipedia/commons/b/bd/Zomato_Logo.svg"));
                        offerRepository.save(new Offer("SWIGGY", "JUMBO", "Free Delivery on Jumbo Packs", "FREE DEL",
                                        "https://upload.wikimedia.org/wikipedia/en/1/12/Swiggy_logo.svg"));
                        offerRepository.save(new Offer("ZOMATO", "ZOMATO30", "30% OFF up to ₹75", "30% OFF",
                                        "https://upload.wikimedia.org/wikipedia/commons/b/bd/Zomato_Logo.svg"));
                        offerRepository.save(new Offer("SWIGGY", "PARTY", "Extra 15% OFF on Party Orders", "15% OFF",
                                        "https://upload.wikimedia.org/wikipedia/en/1/12/Swiggy_logo.svg"));
                        offerRepository.save(new Offer("ZOMATO", "TASTY", "Get a free dessert on orders above ₹500",
                                        "FREE DESSERT",
                                        "https://upload.wikimedia.org/wikipedia/commons/b/bd/Zomato_Logo.svg"));

                        if (questionRepository.count() > 0)
                                return;

                        Question q1 = new Question(null, "How hungry are you right now?", null);
                        q1.setOptions(Arrays.asList(
                                        new Option(null, "I could eat a horse! (Starving)", "HEAVY"),
                                        new Option(null, "Little bit peckish (Snack time)", "LIGHT"),
                                        new Option(null, "Just want something tasty", "STREET")));
                        questionRepository.save(q1);

                        Question q2 = new Question(null, "Spice tolerance level?", null);
                        q2.setOptions(Arrays.asList(
                                        new Option(null, "Bring on the heat! (Spicy)", "SPICY"),
                                        new Option(null, "Mild and creamy please", "CREAMY"),
                                        new Option(null, "Sweet tooth craving", "SWEET")));
                        questionRepository.save(q2);

                        Question q3 = new Question(null, "Veg or Non-Veg?", null);
                        q3.setOptions(Arrays.asList(
                                        new Option(null, "Pure Veg", "VEG"),
                                        new Option(null, "Require Meat", "NONVEG"),
                                        new Option(null, "Either is fine", "STREET")));
                        questionRepository.save(q3);

                        Question q4 = new Question(null, "What's the budget?", null);
                        q4.setOptions(Arrays.asList(
                                        new Option(null, "Broke student (Cheap eats)", "CHEAP"),
                                        new Option(null, "Treating myself (Royal)", "ROYAL"),
                                        new Option(null, "Standard meal", "DINNER")));
                        questionRepository.save(q4);

                        Question q5 = new Question(null, "Texture preference?", null);
                        q5.setOptions(Arrays.asList(
                                        new Option(null, "Crispy and crunchy", "CRISPY"),
                                        new Option(null, "Soft and Melt-in-mouth", "SOFT"),
                                        new Option(null, "Rice based", "RICE")));
                        questionRepository.save(q5);

                        Question q6 = new Question(null, "Time of day?", null);
                        q6.setOptions(Arrays.asList(
                                        new Option(null, "Good Morning", "BREAKFAST"),
                                        new Option(null, "Lunch break", "LUNCH"),
                                        new Option(null, "Dinner time", "DINNER"),
                                        new Option(null, "Midnight cravings", "SNACK")));
                        questionRepository.save(q6);

                        Question q7 = new Question(null, "Temperature?", null);
                        q7.setOptions(Arrays.asList(
                                        new Option(null, "Piping hot", "HOT"),
                                        new Option(null, "Cool and refreshing", "COLD"),
                                        new Option(null, "Room temp", "SNACK")));
                        questionRepository.save(q7);

                        Question q8 = new Question(null, "Cuisine preference?", null);
                        q8.setOptions(Arrays.asList(
                                        new Option(null, "North Indian", "NORTH"),
                                        new Option(null, "South Indian", "SOUTH"),
                                        new Option(null, "Maharashtra / Pune Local", "MARATHI")));
                        questionRepository.save(q8);

                        Question q9 = new Question(null, "Mood?", null);
                        q9.setOptions(Arrays.asList(
                                        new Option(null, "Healthy", "HEALTHY"),
                                        new Option(null, "Comfort food (Indulgent)", "BUTTERY"),
                                        new Option(null, "Festive", "FESTIVE")));
                        questionRepository.save(q9);

                        Question q10 = new Question(null, "Last one: Fried or Baked?", null);
                        q10.setOptions(Arrays.asList(
                                        new Option(null, "Deep Fried Goodness", "FRIED"),
                                        new Option(null, "Tandoori / Baked", "NORTH"),
                                        new Option(null, "Don't care", "STREET")));
                        questionRepository.save(q10);

                };
        }
}
