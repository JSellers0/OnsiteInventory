package example.jsellers0.onsiteinventory

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText

//ToDo: Set up layout and code to allow user to define inbound/outbound showcodes

const val SHOWCODE = "example.jsellers0.onsiteinventory.MESSAGE"

class StartInventory : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_inventory)
    }

    fun newInventory(view: View) {
        /*val shipmentType = intent.getStringExtra(SHIPMENT_TYPE)
        when(shipmentType) {
            "inbound" -> {/*ToDo: new inbound inventory procedures*/}
            "outbound" -> {/*ToDo: new outbound inventory procedures*/}
        }*/
        val editText = findViewById<EditText>(R.id.showcode_entry)
        val showcode = editText.text.toString()
        val newInventory = Intent(this, Inventory::class.java).apply {
            putExtra(SHOWCODE, showcode)
        }
        startActivity(newInventory)
    }

    fun existingInventory(view: View) {
        /*val shipmentType = intent.getStringExtra(SHIPMENT_TYPE)
        when (shipmentType) {
            "inbound" -> {/*ToDo: existing inbound inventory procedures*/}
            "outbound" -> {/*ToDo: existing outbound inventory procedures*/}
        }*/
        val editText = findViewById<EditText>(R.id.showcode_entry)
        val showcode = editText.text.toString()
        val existingInventory = Intent(this, Inventory::class.java).apply {
            putExtra(SHOWCODE, showcode)
        }
        startActivity(existingInventory)
    }
}
