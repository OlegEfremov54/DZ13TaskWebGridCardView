package com.example.dz13taskwebgridcardview

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var toolbarMain:Toolbar
    private lateinit var gridViewGV: GridView
    private var list = mutableListOf(
        GridViewModal("Рутюб", R.drawable.rutube, "https://rutube.ru/"),
        GridViewModal("Ютюб", R.drawable.youtube, "https://www.youtube.com/"),
        GridViewModal("Яндекс погода", R.drawable.yandex_weather, "https://yandex.ru/pogoda/saint-petersburg"),
        GridViewModal("Яндекс карты", R.drawable.yamup, "https://yandex.ru/maps"),
        GridViewModal("Википедия", R.drawable.wiki, "https://ru.wikipedia.org/wiki/Заглавная_страница"),
        GridViewModal("Авито", R.drawable.avito, "https://www.avito.ru/"),
        GridViewModal("БКС", R.drawable.bks, "https://bcs.ru/"),
        GridViewModal("Атон", R.drawable.aton, "https://www.aton.ru/")
    )
    private var urlString:String = ""


    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Инициализация Тулбар
        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = " Мобильный браузер"
        toolbarMain.subtitle = " Вер.1.Главная страница"
        toolbarMain.setLogo(R.drawable.db)

        // Заполнение Грид виев
        gridViewGV = findViewById(R.id.gridViewGV)

        val adapter = GridViewAdapter(list = list, this@MainActivity)
        gridViewGV.adapter = adapter

        // Обработка нажатия выбранного сайта
        gridViewGV.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            Toast.makeText(
                applicationContext,
                "Выбран сайт: ${list[position].adres}",
                Toast.LENGTH_SHORT
            ).show()
            urlString = "${list[position].adres}"
           val intent= Intent(this, MainActivity2::class.java)
              intent.putExtra("URL", urlString)
              startActivity(intent)
           //startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(list[position].adres)))
        }

    }
    // Активация Меню
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.infoMenuMain -> {
                Toast.makeText(applicationContext, "Автор Ефремов О.В. Создан 29.11.2024",
                    Toast.LENGTH_LONG).show()
            }
            R.id.exitMenuMain ->{
                Toast.makeText(applicationContext, "Работа приложения завершена",
                    Toast.LENGTH_LONG).show()
                finishAffinity()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}