package com.icg.dialogs

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.icg.dialogs.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.simpleDialog.setOnClickListener {
            showFirstSimpleDialog()
        }
        binding.simpleDialog2.setOnClickListener {
            showSecondSimpleDialog()
        }
        binding.singleChoiceDialog.setOnClickListener {
            showSingleChoiceDialog()
        }
        binding.multiChoiceDialog.setOnClickListener {
            showMultiChoiceDialog()
        }
        binding.progressDialog.setOnClickListener {
            showLoadingProgressDialog()
        }
        binding.progressDialog2.setOnClickListener {
            showCircularProgressDialog()
        }
        binding.datePicker.setOnClickListener {
            showDatePicker()
        }
        binding.timePicker.setOnClickListener {
            showTimePicker()
        }
        binding.bottomDialog.setOnClickListener {
            showBottomDialog()
        }

    }
    private fun showFirstSimpleDialog() {
        val dialog = AlertDialog.Builder(this).setCancelable(false).create()
        val itemView =  View.inflate(this,R.layout.simple_dialog,null)
        val buttonOkay = itemView.findViewById<Button>(R.id.btnOkay)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        buttonOkay.setOnClickListener { dialog.dismiss() }
        dialog.setView(itemView)
        dialog.show()
    }
    private fun showSecondSimpleDialog() {
        val dialog = AlertDialog.Builder(this)

        dialog.setTitle("This is a simple dialog")
        dialog.setMessage("This is message area")
        dialog.setPositiveButton(
            R.string.okay
        ) { dialogs, which ->
            dialogs.dismiss()

        }
        val createDialog = dialog.create()
        createDialog.show()
    }

    private fun showSingleChoiceDialog() {
        val items = arrayOf("item1", "item2", "item3")
        val itemChecked = 0
        AlertDialog.Builder(this)
            .setSingleChoiceItems(items, itemChecked, null)
            .setPositiveButton(R.string.okay,
                DialogInterface.OnClickListener { dialog, whichButton ->
                    dialog.dismiss()
                    val selectedPosition = (dialog as AlertDialog).listView.checkedItemPosition
                    Toast.makeText(this, "Item selected: $selectedPosition", Toast.LENGTH_SHORT).show()

                })
            .show()

    }

    private fun showMultiChoiceDialog() {
        val items = arrayOf("item1", "item2", "item3")
        val selectedItems = ArrayList<Int>()
        val checkedItems = BooleanArray(items.size)


        AlertDialog.Builder(this).setMultiChoiceItems(
            items, checkedItems
        ) { dialog, indexSelected, isChecked ->
            if (isChecked) {
                selectedItems.add(indexSelected)
            } else if (selectedItems.contains(indexSelected)) {
                selectedItems.remove(Integer.valueOf(indexSelected))
            }
        }.setPositiveButton(R.string.okay) { dialog, id ->
            dialog.dismiss()
            for (i in items.indices){
                if (checkedItems.get(i)){
                    Toast.makeText(this, "${items.get(i)} is selected",Toast.LENGTH_SHORT).show()
                }
            }

        }.show()
    }

    private fun showLoadingProgressDialog() {
        val dialog = AlertDialog.Builder(this)
        val itemView =  View.inflate(this,R.layout.linear_progress_bar_dialog,null)
        dialog.setPositiveButton(
            com.icg.dialogs.R.string.okay
        ) { dialog, which ->
            dialog.dismiss()

        }

        dialog.setView(itemView)
        dialog.show()
    }

    private fun showCircularProgressDialog() {
        val dialog = AlertDialog.Builder(this)
        val itemView =  View.inflate(this,R.layout.circular_progress,null)
        dialog.setPositiveButton(
            com.icg.dialogs.R.string.okay
        ) { dialog, which ->
            dialog.dismiss()

        }

        dialog.setView(itemView)
        dialog.show()
    }

    private fun showDatePicker() {

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)


        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, yearSelected, monthOfYear, dayOfMonth ->
            Toast.makeText(this, "$yearSelected $monthOfYear,$dayOfMonth", Toast.LENGTH_SHORT).show()

        }, year, month, day)

        datePickerDialog.show()
    }

    @SuppressLint("SimpleDateFormat")
    private fun showTimePicker() {
        val calendar = Calendar.getInstance()

        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)

            val timeSelected = SimpleDateFormat("HH:mm").format(calendar.time)
            Toast.makeText(this, timeSelected, Toast.LENGTH_SHORT).show()
        }
        TimePickerDialog(this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()



    }

    private fun showBottomDialog() {

        val dialog = BottomSheetDialog(this)
        val itemView = View.inflate(this, R.layout.simple_dialog, null)
        val cancelImage = itemView.findViewById(R.id.cancelBtn) as ImageView
        cancelImage.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setContentView(itemView)

        dialog.setTitle("Bottom Dialog")
        dialog.show()
    }




}