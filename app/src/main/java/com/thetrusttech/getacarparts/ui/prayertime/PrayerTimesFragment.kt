package com.thetrusttech.getacarparts.ui.prayertime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.databinding.ListItemBinding
import com.thetrusttech.getacarparts.base.RecyclerAdapter
import org.json.XML
import org.json.JSONObject


class PrayerTimesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prayer_times, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        anb()

    }

    fun anb() {
        val PRETTY_PRINT_INDENT_FACTOR = 2

        val xmlStr = """
  	<Author>
	   <address>
	      <street>Internet Broadline</street>
	      <postcode>123456</postcode>
	   </address>
	   <name>bezkoder</name>
	   <age>26</age>
	</Author>
  """

        val jsonObj = XML.toJSONObject(xmlStr)
        val jsonPrettyPrintString = jsonObj.toString(PRETTY_PRINT_INDENT_FACTOR)

        println(jsonPrettyPrintString)
    }

}