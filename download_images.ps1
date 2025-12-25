$dishes = @{
    "panipuri.jpg" = "https://upload.wikimedia.org/wikipedia/commons/5/5c/Crispy_Pani_Puri.jpg"
    "vadapav.jpg" = "https://upload.wikimedia.org/wikipedia/commons/4/4e/Vadapav.png"
    "pavbhaji.jpg" = "https://upload.wikimedia.org/wikipedia/commons/4/4a/Bambayya_Pav_bhaji.jpg"
    "samosa.jpg" = "https://upload.wikimedia.org/wikipedia/commons/c/cb/Samosa_dish.jpg"
    "cholebhature.jpg" = "https://upload.wikimedia.org/wikipedia/commons/9/91/Chole_Bhature_delhi_street_food_wikicontribute.jpg"
    "butterchicken.jpg" = "https://upload.wikimedia.org/wikipedia/commons/3/30/Butter_Chicken_served_in_a_tray.jpg"
    "biryani.jpg" = "https://upload.wikimedia.org/wikipedia/commons/5/5a/Hyderabadi_Chicken_Biryani.jpg"
    "masaladosa.jpg" = "https://upload.wikimedia.org/wikipedia/commons/9/9f/Masala_dosa_served_with_chutneys_and_sambar.jpg"
    "dalmakhani.jpg" = "https://upload.wikimedia.org/wikipedia/commons/3/33/Dal_Makhani_01.jpg"
    "palakpaneer.jpg" = "https://upload.wikimedia.org/wikipedia/commons/6/64/Palak_Paneer_01.jpg"
    "gulabjamun.jpg" = "https://upload.wikimedia.org/wikipedia/commons/8/88/Gulab_Jamun_01.jpg"
    "jalebi.jpg" = "https://upload.wikimedia.org/wikipedia/commons/1/1c/Imarti_or_Jalebi.jpg"
}

$destBase = "src/main/resources/static/images"
mkdir $destBase -Force

foreach ($key in $dishes.Keys) {
    echo "Downloading $key ..."
    $url = $dishes[$key]
    $output = Join-Path $destBase $key
    try {
        Invoke-WebRequest -Uri $url -OutFile $output -UserAgent "Mozilla/5.0"
    } catch {
        echo "Failed to download $key from $url"
    }
}
