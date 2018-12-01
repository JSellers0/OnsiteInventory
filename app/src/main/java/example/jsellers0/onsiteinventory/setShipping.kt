package example.jsellers0.onsiteinventory

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import org.json.JSONArray
import java.io.File

class setShipping : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_shipping)
    }

    fun addShipment(view: View){
        val filename = intent.getStringExtra(FILENAME)
        val file = File(applicationContext.filesDir, filename)
        var inventory = file.bufferedReader().readLines()
        val inventoryJSON = JSONArray(inventory.toString())
        var showJSON = inventoryJSON.getJSONObject(0)
        var shipJSON = inventoryJSON.getJSONObject(1)
        val showText = findViewById<EditText>(R.id.show_ship)
        val show = showText.text.toString()

        val emailText = findViewById<EditText>(R.id.email_ship)
        val email = emailText.text.toString()

        shipJSON.put(show, email)
        //ToDo: add back to array and save to file
        //ToDo: setShowText and emailText back to hint text
    }

    fun startInventory(view: View) {
        val startInventory = Intent(this, Inventory::class.java)
        startActivity(startInventory)
    }
}
