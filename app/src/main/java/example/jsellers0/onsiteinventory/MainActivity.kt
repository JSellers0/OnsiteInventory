package example.jsellers0.onsiteinventory

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

const val SHIPMENT_TYPE = "example.jsellers0.onsiteinventory.MESSAGE"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startInventory(view: View) {
        val shipment = when (view.id) {
            R.id.inbound_button -> {"inbound"}
            R.id.outbound_button -> {"outbound"}
            else -> {"ERROR"}
        }
        val begin = Intent(this, StartInventory::class.java).apply {
            putExtra(SHIPMENT_TYPE, shipment)
        }
        startActivity(begin)
    }
}
