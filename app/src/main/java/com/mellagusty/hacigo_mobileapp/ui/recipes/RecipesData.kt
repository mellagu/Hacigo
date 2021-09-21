package com.mellagusty.hacigo_mobileapp.ui.recipes

import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.data.firestore.recipe.RecipesEntity

fun RecipesData(): MutableList<RecipesEntity>{
    val recipes = mutableListOf<RecipesEntity>()
    recipes.apply {
        add(
            RecipesEntity(
                "Bubur Pisang Jeruk",
                "MPASI bergizi yang pas untuk anak anda.",
                arrayListOf("pisang","jeruk"),
                "Dalam 100gr pisang terkandung 88kcal (kalori) dengan jumlah karbohidrat 23gr. Zat lain yang penting bagi kesehatan tubuh adalah vitamin C, zat besi, manesium, dan kalsium. Adapun pada 100gr jeruk terkandung 44kcal (kalori) dengan kandungan vitamin C yang tinggi yang baik untuk kesehatan tubuh, serta zat lain seperti zat besi, magnesium, serta kalsium.",
                arrayListOf("1 buah pisang ambon","Tepung beras putih secukupnya","Perasan jeruk","Air"),
                arrayListOf("1. Kerok halus daging buah pisang ambon",
                    "2. Campur dengan tepung beras putih dan air",
                    "3. Tambahkan perasan jeruk",
                    "4. Aduk hingga rata",
                    "5. Bubur siap disajikan"),
                R.drawable.recipe1
            )
        )
        add(
            RecipesEntity(
                "Jus Pisang dan Pir",
                "Kaya akan vitamin dan serat, wajib di coba sebelum sarapan.",
                arrayListOf("pisang","pir"),
                "Dalam 100gr pisang terkandung 88kcal (kalori) dengan jumlah karbohidrat 23gr. Zat lain yang penting bagi kesehatan tubuh adalah vitamin C, zat besi, manesium, dan kalsium. Selain itu, 100gr pir mengandung 57kcal (kalori), vitamin C, magnesium, dan kalsium yang dibutuhkan bayi.",
                arrayListOf("1/2 potong buah pir","1/3 potong buah pisang raja"),
                arrayListOf("1. Kupas masing-masing kulit buah pir dan pisang",
                    "2. Untuk buah pir, buang bijinya",
                    "3. Potong pir dan pisang menjadi kecil-kecil",
                    "4. Haluskan buah dengan blender hingga hancur dan halus",
                    "5. Saring buah yang sudah dihaluskan",
                    "6. Jus siap disajikan"),
                R.drawable.recipe2
            )
        )
        add(
            RecipesEntity(
                "Nanas untuk Bayi",
                "Cemilan untuk si bayi MPASI",
                arrayListOf("nanas"),
                "Nanas sebanyak 100gr mengandung 50kcal (kalori) serta memiliki kandungan vitamin C yang tinggi yang dapat meningkatkan daya tahan tubuh dan menjaga kesehatan tulang.",
                arrayListOf("Nanas"),
                arrayListOf("1. Dihaluskan",
                    "2. Bisa dicampur dengan yoghurt / pisang / jagung / wortel",
                    "3. Disarankan dikonsumsi sebagai camilan"),
                R.drawable.recipe3
            )
        )
        add(
            RecipesEntity(
                "Puree Apel dan Pir",
                "MPASI bergizi dan lezat untuk anak anda.",
                arrayListOf("apel","pir"),
                "Dalam 100gr apel terkandung 52kcal (kalori), kalium, vitamin C, magnesium, dan kalsium serta kaya antioksidan. Selain itu, 100gr pir mengandung 57kcal (kalori), vitamin C, magnesium, dan kalsium yang dibutuhkan bayi.",
                arrayListOf("1 buah apel","1 buah pir","Air 300 mL"),
                arrayListOf("1. Masukkan apel dan pir ke dalam panci",
                    "2. Tambahkan air",
                    "3. Rebus pada suhu rendah / kukus juga bisa",
                    "4. Aduk sesekali hingga lunak",
                    "5. Masukkan ke dalam blender agar lebih halus",
                    "6. Puree siap disajikan"),
                R.drawable.recipe4
            )
        )
        add(
            RecipesEntity(
                "Puree Pisang, Ubi, dan Apel",
                "MPASI sehat bergizi.",
                arrayListOf("pisang","ubi","apel"),
                "Dalam 100gr pisang terkandung 88kcal (kalori) dengan jumlah karbohidrat 23gr. Zat lain yang penting bagi kesehatan tubuh adalah vitamin C, zat besi, manesium, dan kalsium. Ubi adalah karbohidrat dengan serat yang tinggi dan mengandung zat lain seperti magnesium dan kalsium. Dalam 100gr apel terkandung 52kcal (kalori), kalium, vitamin C, magnesium, dan kalsium.",
                arrayListOf("1/4 buah pisang","1 buah ubi","1 buah apel","susu formula yang sedang dikonsumsi bayi atau ASI"),
                arrayListOf("1. Kupas kulit ubi, apel, dan pisang",
                    "2. Kukus ubi dan apel hingga lembut",
                    "3. Sembari menunggu, haluskan pisang di dalam mangkok atau wadah makan untuk bayi",
                    "4. Setelah dikukus, campurkan ubi dan apel dengan pisang yang sudah dihaluskan",
                    "5. Aduk ketiga bahan hingga merata",
                    "6. Tambahkan susu formula atau ASI",
                    "7. Aduk sekali lagi hinnga merata",
                    "8. Puree pisang, ubi, dan apel siap disajikan"),
                R.drawable.recipe4
            )
        )
        add(
            RecipesEntity(
                "Puree Brokoli dan Kembang Kol",
                "Kaya akan vitamin C dan zat besi, anak anda akan memintanya lagi.",
                arrayListOf("brokoli","kembang kol"),
                "Brokoli dan kembang kol adalah sayuran tinggi serat yang bagus dikonsumsi oleh bayi. Brokoli memiliki kandungan kalsium, fosfor, riboflavin, dan vitamin K yang cukup tinggi. Dalam kembang kol juga terkandung vitamin C, riboflavin, vitamin K, dan kaya antioksidan.",
                arrayListOf("Brokoli","Kembang Kol","Susu yang bisa dikonsumsi bayi"),
                arrayListOf("1. Cuci brokoli dan kembang kol",
                    "2. Potong kecil-kecil brokoli dan kembang kol",
                    "3. Haluskan brokoli dan kembang kol dengan cara di blender",
                    "4. Masukkan susu",
                    "5. Puree siap diberikan"),
                R.drawable.recipe6
            )
        )
        add(
            RecipesEntity(
                "Tumis Jamur Putren",
                "Enak, sederhana, dan si kecil akan minta nambah!",
                arrayListOf("jamur","jagung"),
                "Jamur adalah sumber vitamin B3, kaya kolagen, dan kaya antioksidan. Sementara itu, putren adalah karbohidrat dengan tinggi serat yang baik untuk perkembangan bayi.",
                arrayListOf("Segenggam jamur tiram","200 gr putren (jagung muda)","Daun bawang secukupnya","Air secukupnya","Minyak","Saos lada hitam secukupnya","Bawang putih 4 siung","Bawang merah 5 siung","Cabe merah besar 1 / optional jumlahnya","Daun jeruk 2 lembar"),
                arrayListOf("1. Siangi jamur dan putren, kemudian iris tipis",
                    "2. Iris bawang merah, bawang putih, cabe merah, dan daun bawang",
                    "3. Panaskan minyak",
                    "4. Tumis daun bawang, bawang merah, bawang putih terlebih dahulu",
                    "5. Masukkan cabe merah dan daun jeruk yang disobek",
                    "6. Tambahkan air secukupnya",
                    "7. Campurkan saos lada hitam secukupnya",
                    "8. Masukkan jamur dan putren",
                    "9. Tunggu hingga matang",
                    "10. Cicip terlebih dahulu sebelum disajikan untuk menyesuaikan kesukaan balita"),
                R.drawable.recipe7
            )
        )
    }
    return  recipes
}