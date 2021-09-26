package com.icg.training

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.icg.training.adapter.AndroidInfoAdapter
import com.icg.training.data_provider.DataProvider
import com.icg.training.data_provider.MinSdkList
import com.icg.training.data_provider.VersionList
import com.icg.training.databinding.FragmentFirstBinding
import com.icg.training.model.InfoModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_dialog.*
import kotlinx.android.synthetic.main.fragment_first.*
import java.lang.StringBuilder
import java.util.*
import kotlin.math.min

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {


    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).supportActionBar?.title = "RecyclerView"
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        initView()
        initClicks()
        loadData()

        return binding.root

    }

    private fun initView() {
        binding.recyclerAndroidVersions.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = AndroidInfoAdapter(::onItemClicked, ::showDeleteAlert)
        }
       val fab = activity?.findViewById<FloatingActionButton>(R.id.fab)
        fab?.setOnClickListener {
            showCustomDialog()
        }
    }

    private fun showCustomDialog() {
        with(context?.let { Dialog(it) }){
            this?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            this?.setContentView(R.layout.custom_dialog)
            val edtName = this?.findViewById<TextInputEditText>(R.id.edtName) as TextInputEditText
            val versionList = this?.findViewById<Spinner>(R.id.versionList) as Spinner
            val releasedOn = this?.findViewById<TextView>(R.id.datePicker)
            val minSdk = this?.findViewById<Spinner>(R.id.minSdk)
            val desc = this?.findViewById<TextInputEditText>(R.id.desc)

            ArrayAdapter.createFromResource(context,
            R.array.version_array,android.R.layout.simple_spinner_item).also {
                arrayAdapter ->
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                versionList.adapter = arrayAdapter
            }
            ArrayAdapter.createFromResource(context,
            R.array.min_sdk,android.R.layout.simple_spinner_item).also {
                adapter->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                minSdk.adapter = adapter
            }
            releasedOn.setOnClickListener {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)


                val datePickerDialog = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, yearSelected, monthOfYear, dayOfMonth ->
                   releasedOn.setText(StringBuilder().append(dayOfMonth).append("/").append(monthOfYear).append("/").append(yearSelected))

                }, year, month, day)

                datePickerDialog.show()
            }

            this?.findViewById<AppCompatButton>(R.id.btnAdd).setOnClickListener {
                if (edtName.text.toString().trim().isEmpty()){
                    edtName.error = "Android Name is required"
                }else if(releasedOn.text.toString().trim().isEmpty()){
                    releasedOn.error = "Release date is required"
                }else if(edtDesc.text.toString().trim().isEmpty()){
                    edtDesc.error = "Description is required"
                }else{
                    val versionSelected = versionList.selectedItem.toString()
                    val minSdkSelected = minSdk.selectedItem.toString().toInt()
                    val name = edtName.text.toString()
                    val desc = edtDesc.text.toString()
                    val releasedDate = releasedOn.text.toString()

                    val infoModel = InfoModel(versionSelected,name,minSdkSelected,desc,releasedDate)
                    (binding.recyclerAndroidVersions.adapter as AndroidInfoAdapter).notifyItemInserted(0)
                    (binding.recyclerAndroidVersions.adapter as AndroidInfoAdapter).insertItem(0,infoModel)
                    Snackbar.make(binding.recyclerAndroidVersions, "Item inserted at ${0}",Snackbar.LENGTH_SHORT).show()
                    dismiss()

                }


            }
            window?.apply {
                setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
            show()
        }
    }
  private fun showUpdateDialog(infoModel: InfoModel,position:Int){
      with(context?.let { Dialog(it) }){
          this?.requestWindowFeature(Window.FEATURE_NO_TITLE)
          this?.setContentView(R.layout.custom_dialog)
          val edtName = this?.findViewById<TextInputEditText>(R.id.edtName) as TextInputEditText
          val versionList = this?.findViewById<Spinner>(R.id.versionList) as Spinner
          val releasedOn = this?.findViewById<TextView>(R.id.datePicker)
          val minSdk = this?.findViewById<Spinner>(R.id.minSdk)
          val desc = this?.findViewById<TextInputEditText>(R.id.edtDesc)


          val butnUpdate = this?.findViewById<AppCompatButton>(R.id.btnAdd) as AppCompatButton
          butnUpdate.setText("Update")
          edtName.setText(infoModel.androidName)

          releasedOn.setText(infoModel.releaseDate)
          desc.setText(infoModel.androidDesc)

          val arrayList = VersionList.getVersion()

          val adapter= ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, arrayList)

          versionList.adapter = adapter
          versionList.setSelection(position)

          val mArrayList = MinSdkList.getMinSdk()
          val minSdkAdapter = ArrayAdapter<Int>(context, android.R.layout.simple_spinner_item, mArrayList)
          minSdk.adapter = minSdkAdapter
          minSdk.setSelection(position)
          releasedOn.setOnClickListener {
              val calendar = Calendar.getInstance()
              val year = calendar.get(Calendar.YEAR)
              val month = calendar.get(Calendar.MONTH)
              val day = calendar.get(Calendar.DAY_OF_MONTH)


              val datePickerDialog = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, yearSelected, monthOfYear, dayOfMonth ->
                  releasedOn.setText(StringBuilder().append(dayOfMonth).append("/").append(monthOfYear).append("/").append(yearSelected))

              }, year, month, day)

              datePickerDialog.show()
          }

          butnUpdate.setOnClickListener {
              if (edtName.text.toString().trim().isEmpty()){
                  edtName.error = "Android Name is required"
              }else if(releasedOn.text.toString().trim().isEmpty()){
                  releasedOn.error = "Release date is required"
              }else if(edtDesc.text.toString().trim().isEmpty()){
                  edtDesc.error = "Description is required"
              }else{
//                  val getVersionList = (binding.recyclerAndroidVersions.adapter as AndroidInfoAdapter).getItemCount()

                  val versionSelected = versionList.selectedItem.toString()
                  val minSdkSelected = minSdk.selectedItem.toString().toInt()
                  val name = edtName.text.toString()
                  val desc = edtDesc.text.toString()
                  val releasedDate = releasedOn.text.toString()

                  val infoModel = InfoModel(versionSelected,name,minSdkSelected,desc,releasedDate)

                  (binding.recyclerAndroidVersions.adapter as AndroidInfoAdapter).notifyItemChanged(position)
                  (binding.recyclerAndroidVersions.adapter as AndroidInfoAdapter).updateItem(position,infoModel)



                  Snackbar.make(binding.recyclerAndroidVersions, "${infoModel.androidName} updated",Snackbar.LENGTH_SHORT).show()
                  dismiss()

              }


          }
          window?.apply {
              setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
              setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
          }
          show()
      }
  }
    private fun initClicks(){

    }

    private fun onItemClicked(selectedItem:InfoModel?,position: Int){
        showUpdateDialog(selectedItem!!,position)
    }
    private fun loadData(){
        (binding.recyclerAndroidVersions.adapter as AndroidInfoAdapter).versionListItems =
            DataProvider.getAndroidDetails()
    }
    private fun showDeleteAlert(deleteItemPosition:Int){
        with(context?.let { AlertDialog.Builder(context) }){
            this?.setTitle("Delete")
            this?.setMessage("Are you sure you want to delete this item?")
            this?.setNegativeButton("NO"){dialogInterface,_->
                dialogInterface.dismiss()
            }
            this?.setPositiveButton("YES"){dialogInterface,_->
                val infoModel = (binding.recyclerAndroidVersions.adapter as AndroidInfoAdapter).versionListItems?.get(deleteItemPosition)
                val getVersionList = (binding.recyclerAndroidVersions.adapter as AndroidInfoAdapter).getItemCount()
                val itemCount = getVersionList - deleteItemPosition
                (binding.recyclerAndroidVersions.adapter as AndroidInfoAdapter).notifyItemRemoved(deleteItemPosition)

                (binding.recyclerAndroidVersions.adapter as AndroidInfoAdapter).deleteItem(deleteItemPosition)
                Snackbar.make(binding.recyclerAndroidVersions, "${infoModel?.androidName} is deleted", Snackbar.LENGTH_SHORT).show()
            }
            this?.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}