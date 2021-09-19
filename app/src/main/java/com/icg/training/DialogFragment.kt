package com.icg.training


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.icg.training.databinding.FragmentDialogBinding
import org.xmlpull.v1.XmlPullParser
import java.text.SimpleDateFormat
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS
import kotlin.collections.ArrayList


class DialogFragment : Fragment() {
    private var _binding:FragmentDialogBinding?=null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
        val dialog = AlertDialog.Builder(context).setCancelable(false).create()
        val itemView =  View.inflate(context,R.layout.simple_dialog,null)
        val buttonOkay = itemView.findViewById<Button>(R.id.btnOkay)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        buttonOkay.setOnClickListener { dialog.dismiss() }
        dialog.setView(itemView)
        dialog.show()
    }
    private fun showSecondSimpleDialog() {
        val dialog = AlertDialog.Builder(context)

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
        AlertDialog.Builder(context)
            .setSingleChoiceItems(items, itemChecked, null)
            .setPositiveButton(R.string.okay,
                DialogInterface.OnClickListener { dialog, whichButton ->
                    dialog.dismiss()
                    val selectedPosition = (dialog as AlertDialog).listView.checkedItemPosition
                   Toast.makeText(context, "Item selected: $selectedPosition", Toast.LENGTH_SHORT).show()

                })
            .show()

    }

    private fun showMultiChoiceDialog() {
        val items = arrayOf("item1", "item2", "item3")
        val selectedItems = ArrayList<Int>()
        val checkedItems = BooleanArray(items.size)


        AlertDialog.Builder(context).setMultiChoiceItems(
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
                    Toast.makeText(context, "${items.get(i)} is selected",Toast.LENGTH_SHORT).show()
                }
            }

        }.show()
    }

    private fun showLoadingProgressDialog() {
        val dialog = AlertDialog.Builder(context)
        val itemView =  View.inflate(context,R.layout.linear_progress_bar_dialog,null)
        dialog.setPositiveButton(
            com.icg.training.R.string.okay
        ) { dialog, which ->
            dialog.dismiss()

        }

        dialog.setView(itemView)
        dialog.show()
    }

    private fun showCircularProgressDialog() {
        val dialog = AlertDialog.Builder(context)
        val itemView =  View.inflate(context,R.layout.circular_progress,null)
        dialog.setPositiveButton(
            com.icg.training.R.string.okay
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


        val datePickerDialog = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, yearSelected, monthOfYear, dayOfMonth ->
            Toast.makeText(context, "$yearSelected $monthOfYear,$dayOfMonth", Toast.LENGTH_SHORT).show()

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
            Toast.makeText(context, timeSelected, Toast.LENGTH_SHORT).show()
        }
        TimePickerDialog(context, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()



    }

    private fun showBottomDialog() {

        val dialog = BottomSheetDialog(requireContext())
        val itemView = View.inflate(context, R.layout.simple_dialog, null)
        val cancelImage = itemView.findViewById(R.id.cancelBtn) as ImageView
        cancelImage.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setContentView(itemView)

        dialog.setTitle("Bottom Dialog")
        dialog.show()
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}