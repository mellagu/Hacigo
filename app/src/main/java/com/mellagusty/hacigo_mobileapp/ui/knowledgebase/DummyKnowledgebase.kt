package com.mellagusty.hacigo_mobileapp.ui.knowledgebase

import com.mellagusty.hacigo_mobileapp.data.local.knowledgebase.KnowledgebaseEntity

fun DummyKnowledgebase(): MutableList<KnowledgebaseEntity> {
    val kb = mutableListOf<KnowledgebaseEntity>()

    kb.apply {
        add(
            KnowledgebaseEntity(
                "Panduan Usia 0-6 bulan",
                "Pahami kebutuhan anak yang baru lahir, mereka sangat spesial!",
                "1.\tMakanan yang diberikan hanya berupa ASI\n" +
                        "2.\tTanpa ada pemberian     makanan atau minuman lain selain ASI (ASI eksklusif)\n" +
                        "3.\tASI diberikan setiap kali bayi menginginkan\n" +
                        "4.\tSedikitnya 8 kali sehari, pagi siang, sore maupun malam.\n"
            )
        )
        add(
            KnowledgebaseEntity(
                "Panduan Usia 6-9 bulan",
                "Terus pahami kebutuhan anak untuk mencegah stunting",
                "1.\tMemperkenalkan makanan pendamping ASI dalam bentuk makanan lumat (tekstur makanan cair dan lembut)\n" +
                        "Contoh : bubur buah, bubur susu atau bubur sayuran yang dihaluskan, bubur sumsum, nasi tim saring\n" +
                        "2.\tASI tetap diberikan dimana ASI diberikan terlebih dahulu kemudian makanan pendamping ASI.\n" +
                        "3.\tFrekuensi pemberian : 2-3 kali sehari makanan lumat\n" +
                        "4.\tASI sesering mungkin. Jumlah setiap kali makan : 2-3 sendok makan penuh setiap kali makan, secara bertahap ditingkatkan sampai 1/2 mangkuk berukuran 250 ml setiap kali makan\n"
            )
        )
        add(
            KnowledgebaseEntity(
                "Panduan Usia 9-12 bulan",
                "Anak semakin besar, yuk pahami apa saja yang dibutuhkan si kecil!",
                "1.\tMemberikan makanan pendamping ASI dalam bentuk makanan lunak atau lembik (dimasak dengan banyak air dan tampak berair ) atau dicincang yang mudah ditelan anak. Contoh : bubur nasi,  bubur ayam, nasi tim, kentang puri\n" +
                        "2.\tUntuk makanan selingan yang dapat dipegang anak diberikan di antara waktu makan lengkap\n" +
                        "3.\tASI masih tetap diberikan.\n" +
                        "4.\tFrekuensi pemberian : 3-4 kali sehari makanan lembek  +  1-2 kali sehari makanan selingan atau bergantung pada nafsu makan bayi + Pemberian ASI. Jumlah setiap kali makan : ½ sampai dengan ¾ mangkuk berukuran 250 ml\n"
            )
        )
        add(
            KnowledgebaseEntity(
                "Panduan Usia 12-24 bulan",
                "Satu tahun sudah berlalu, kebutuhannya semakin beragam. Apa saja yang dibutuhkan si kecil ya?",
                "1.\tMulai memperkenalkan makanan yang berbentuk padat atau biasa disebut dengan makanan keluarga, tetapi tetap mempertahankan rasa\n" +
                        "2.\tMenghindari memberikan makanan yang dapat mengganggu organ pencernaan, seperti makanan terlalu berbumbu tajam, pedas, terlalu asam atau berlemak.\n" +
                        "3.\tFinger snack  atau makanan yang bisa dipegang seperti cookies, nugget atau potongan sayuran rebus atau buah baik diberikan untuk melatih keterampilan dalam memegang makanan dan merangsang pertumbuhan giginya\n" +
                        "4.\tPemberian ASI masih tetap diteruskan sampai anak berumur dua tahun.\n" +
                        "5.\tFrekuensi pemberian : 3-4 kali sehari makanan keluarga +  1-2 kali sehari makanan selingan atau bergantung pada nafsu makan bayi + Pemberian ASI. Jumlah setiap kali makan : semangkuk penuh berukuran 250 ml\n"
            )
        )
        add(
            KnowledgebaseEntity(
                "Panduan Usia >2 tahun",
                "Pahami kebutuhan balita dan penuhi kebutuhan nutrisinya agar terhindar dari stunting",
                "1.\tPorsi makan anak usia 2 sampai tahun lebih banyak dari sebelumnya. Kebutuhan gizi makro dan mikro harus diperhatikan. Pada usia ini mereka belum bisa mengontrol porsi makanannya sendiri.\n" +
                        "2.\tDi usia ini anak juga mulai sulit makan. Hal ini seiring dengan kemampuan anak untuk menentukan apa yang ia sukai dan inginkan -apa yang tidak. Untuk mengatasi tantangan ini, duduk dan dampingi anak saat makan, buatlah waktu makan selalu menyenangkan dan menjadi hal yang anak tunggu-tunggu. \n"
            )
        )
        add(
            KnowledgebaseEntity(
                "Panduan Usia >5 tahun",
                "Anak sudah semakin besar, yuk temani tumbuh kembangnya dengan nutrisi dan perhatian yang dibutuhkannya",
                "1.\tAnak-anak mulai mengenal junk food. Beberapa anak bahkan mungkin lebih memilih junk food daripada makanan yang diolah orang tuanya. Dia tidak peduli makanan tersebut sehat atau tidak untuk dimakan. Di usia ini kemampuan bersosialisasi anak sudah lebih matang dan dia mulai bermain dengan anak sebayanya. Anak juga mulai melihat jenis makanan apa yang dimakan oleh teman-temannya.\n" +
                        "2.\tDi usia ini , orang tua harus perlu tenaga ekstra menjelaskan pada anak untuk menjelaskan makanan mana yang boleh atau tidak untuk dimakan. Agar anak tidak melulu minta junk food, coba untuk memasak sendiri makanan hingga camilan untuk anak di rumah. Aturlah waktu kapan anak mendapatkan camilan sehat buatan Anda agar anak tidak jajan sembarangan. Jangan lupa, tanyakan padanya, apa yang mereka inginkan dan buatlah makanan yang kreatif dan tampilan yang unik.\n"
            )
        )

    }
    return kb
}