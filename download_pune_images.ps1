$dishes = @{
    "dish_1.jpg" = "Delicious Indian Street Food Vada Pav Mumbai Style professional photography 8k"
    "dish_2.jpg" = "Spicy Misal Pav Kolhapur style topped with farsan and onions professional food photography"
    "dish_3.jpg" = "Pune Sujata Mastani thick mango milkshake with dry fruits and ice cream"
    "dish_4.jpg" = "Bhakarwadi crispy spicy indian snack scenic food photography"
    "dish_5.jpg" = "Kanda Poha flattened rice with turmeric and peanuts indian breakfast"
    "dish_6.jpg" = "Sabudana Khichdi tapioca pearls with peanuts fasting food india"
    "dish_7.jpg" = "Dabeli spicy potato slider with pomegranate and peanuts"
    "dish_8.jpg" = "Sev Puri chaat loaded with chutneys and sev close up"
    "dish_9.jpg" = "Kanda Bhaji crispy onion fritters pakoda monsoon food"
    "dish_10.jpg" = "Pithla Bhakri maharashtrian rural food gram flour curry and millet bread"
    "dish_11.jpg" = "Mumbai Tawa Pulao spicy street fried rice with vegetables"
    "dish_12.jpg" = "Amruttulya Chai Tea in glass cup indian tea stall vibe"
    "dish_13.jpg" = "Bun Maska soft bun with lots of butter and tea"
    "dish_14.jpg" = "Egg Bhurji indian style spicy scrambled eggs with pav bread"
    "dish_15.jpg" = "Puran Poli sweet flatbread stuffed with lentils and jaggery ghee"
    "dish_16.jpg" = "Steamed Modak sweet dumpling coconut filling ganesh chaturthi"
    "dish_17.jpg" = "Bharli Vangi stuffed baby eggplant masala curry maharashtrian"
    "dish_18.jpg" = "Thalipeeth multigrain savory pancake with butter"
    "dish_19.jpg" = "Bombil Fry Bombay Duck fish fry crispy semolina coating"
    "dish_20.jpg" = "Chicken Sukka dry spicy coconut chicken mangalorean style"
    "dish_21.jpg" = "Mutton Rassa red spicy curry kolhapuri style"
    "dish_22.jpg" = "Solkadhi pink coconut milk drink with kumkum kokum"
    "dish_23.jpg" = "Sabudana Vada crispy fried sago patties fasting food"
    "dish_24.jpg" = "Aluvadi colocasia leaf rolls fried snacks patra"
    "dish_25.jpg" = "Kothimbir Vadi cilantro savory cake fried crisps"
    "dish_26.jpg" = "Sheera semolina sweet pudding with saffron and nuts"
    "dish_27.jpg" = "Upma savory semolina breakfast with curry leaves"
    "dish_28.jpg" = "Mango Mastani drink with ice cream scoop"
    "dish_29.jpg" = "Sitaphal Mastani custard apple thick shake"
    "dish_30.jpg" = "Chocolate Sandwich grilled bread with chocolate sauce and chips"
    "dish_31.jpg" = "Cheese Chilli Toast spicy cheesy bread street style"
    "dish_32.jpg" = "Paneer Frankie wrap roll spicy cottage cheese filling"
    "dish_33.jpg" = "Chicken Frankie roti wrap with spicy chicken filling"
    "dish_34.jpg" = "Seekh Kebab grilled minced meat skewers flavorful"
    "dish_35.jpg" = "Chicken Tikka tandoori red marinated chicken chunks"
    "dish_36.jpg" = "Soya Chaap curry rich gravy vegetarian meat substitute"
    "dish_37.jpg" = "Steamed Momos dumplings with spicy red chutney"
    "dish_38.jpg" = "Fried Momos crispy dumplings street snack"
    "dish_39.jpg" = "Ice Gola shaved ice crushed ice colorful syrups stick"
    "dish_40.jpg" = "Matka Kulfi indian ice cream in clay pot saffron pistachio"
    "dish_41.jpg" = "Royal Falooda dessert drink with rose syrup and basil seeds"
    "dish_42.jpg" = "Jalebi Rabdi hot spirals with thick sweet milk"
    "dish_43.jpg" = "Imarti sweet circular flower shaped sweet"
    "dish_44.jpg" = "Moong Dal Halwa rich lentil dessert with ghee"
    "dish_45.jpg" = "Gajanan Vada Pav famous pune style chutney"
    "dish_46.jpg" = "Garden Vada Pav big vada with green chili"
    "dish_47.jpg" = "Spicy Misal Pav extra gravy soup rassa"
    "dish_48.jpg" = "Katakirr Misal spicy red curry with bread"
    "dish_49.jpg" = "Bun Maska Jam bread butter fruit jam"
    "dish_50.jpg" = "Shrewsbury Biscuits buttery cookies pune bakery"
}

$destBase = "src/main/resources/static/images"
mkdir $destBase -Force

foreach ($key in $dishes.Keys) {
    echo "Generating $key ..."
    $prompt = $dishes[$key]
    # URL Encode the prompt
    $encodedPrompt = [Uri]::EscapeDataString($prompt)
    $url = "https://image.pollinations.ai/prompt/$encodedPrompt"
    $output = Join-Path $destBase $key
    try {
        Invoke-WebRequest -Uri $url -OutFile $output -UserAgent "Mozilla/5.0" -TimeoutSec 30
    } catch {
        echo "Failed to download $key"
    }
}
