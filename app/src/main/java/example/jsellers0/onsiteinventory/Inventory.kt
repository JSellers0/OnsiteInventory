package example.jsellers0.onsiteinventory

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class Inventory : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)
    }

    fun loadInventory() {
        //ToDo: Load Inventory
    }

    fun openTouchscreenLabel(view: View) {
        val touchscreen = Intent(this, TouchscreenLabel::class.java)
        startActivity(touchscreen)
    }

    fun openAcerLabel(view: View) {
        val acer = Intent(this, AcerLabel::class.java)
        startActivity(acer)
    }

    fun openServerLabel(view: View) {
        val server = Intent(this, ServerLabel::class.java)
        startActivity(server)
    }

    fun openPelicanLabel(view: View) {
        val pelican = Intent(this, PelicanLabel::class.java)
        startActivity(pelican)
    }

    fun openAnvilLabel(view: View) {
        val anvil = Intent(this, AnvilLabel::class.java)
        startActivity(anvil)
    }
}