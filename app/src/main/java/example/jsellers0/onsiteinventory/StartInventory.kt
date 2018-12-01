package example.jsellers0.onsiteinventory

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.io.File

//ToDo: Figure out how to get keyboard to auto show

const val FILENAME = "example.jsellers0.onsiteinventory.MESSAGE"

class StartInventory : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_inventory)
    }

    private fun validateShowcode(showcode: String): Int {
        val pattern = "[a-zA-Z]{3}?[0-9]{3}?".toRegex()
        if (pattern.matches(showcode)) {
            return 1
        } else {
            return 0
        }
    }

    private fun formatShowcode(showcode: String): String {
        return showcode.substring(0, 2).toUpperCase() + showcode.substring(3, 5)
    }

    private fun initializeInventory(shipmentType: String, showcode: String): String {
        val template = """
            [
                {"showcode":""" + showcode + """},
                {"shipments": []},
                {"cases": []}
            ]
            """.trimIndent()
        var filename = ""
        when (shipmentType) {
            "inbound" -> {
                filename = "$showcode inbound.txt"
                val file = File(applicationContext.filesDir, filename)
                file.writeText(template)
                val message = file.absolutePath
                val duration = Toast.LENGTH_LONG
                val path = Toast.makeText(applicationContext, message, duration)
                path.show()
            }
            "outbound" -> {
                filename = "$showcode outbound.txt"
                applicationContext.openFileOutput(filename, Context.MODE_PRIVATE).use {
                    it.write(template.toByteArray())
                }
            }
        }
        return filename
    }

    private fun verifyExistingInventory(shipmentType: String, showcode: String): String {
        var filename = ""
        var fileExists = false
        when (shipmentType) {
            "inbound" -> {
                filename = "$showcode inbound.txt"
                val file = File(applicationContext.filesDir, filename)
                if (file.exists()) {
                    fileExists = true
                } else {
                    val message =
                        "This show does not have an inventory started.  Please try another code or touch New to start a new inventory."
                    val duration = Toast.LENGTH_SHORT
                    val badEntry = Toast.makeText(applicationContext, message, duration)
                    badEntry.show()
                }
            }
            "outbound" -> {
                filename = "$showcode outbound.txt"
                val file = File(applicationContext.filesDir, filename)
                if (file.exists()) {
                    fileExists = true
                } else {
                    val message =
                        "This show does not have an inventory started.  Please try another code or touch New to start a new inventory."
                    val duration = Toast.LENGTH_SHORT
                    val badEntry = Toast.makeText(applicationContext, message, duration)
                    badEntry.show()
                }
            }
        }
        if (!fileExists) {
            filename = "false"
        }
        return filename
    }

    fun newInventory(view: View) {
        val editText = findViewById<EditText>(R.id.showcode_entry)
        val showcode = editText.text.toString()
        if (validateShowcode(showcode) == 0) {
            val message = "Your entry does not appear to be a showcode.  Please enter a valid showcode."
            val duration = Toast.LENGTH_SHORT
            val badEntry = Toast.makeText(applicationContext, message, duration)
            badEntry.show()
        } else {
            formatShowcode(showcode)
            val shipmentType = intent.getStringExtra(SHIPMENT_TYPE)
            val filename = initializeInventory(shipmentType, showcode)
            val shipping = Intent(this, setShipping::class.java).apply {
                putExtra(FILENAME, filename)
            }
            startActivity(shipping)
        }
    }

    fun existingInventory(view: View) {
        val editText = findViewById<EditText>(R.id.showcode_entry)
        val showcode = editText.text.toString()
        if (validateShowcode(showcode) == 0) {
            val message = "Your entry does not appear to be a showcode.  Please enter a valid showcode."
            val duration = Toast.LENGTH_LONG
            val badEntry = Toast.makeText(applicationContext, message, duration)
            badEntry.show()
        } else {
            formatShowcode(showcode)
            val shipmentType = intent.getStringExtra(SHIPMENT_TYPE)
            val filename = verifyExistingInventory(shipmentType, showcode)
            if (filename != "false") {
                val inventory = Intent(this, Inventory::class.java).apply {
                    putExtra(FILENAME, filename)
                }
                startActivity(inventory)
            }
        }
    }
}