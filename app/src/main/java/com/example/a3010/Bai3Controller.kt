package com.example.a3010

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Bai3Controller : AppCompatActivity() {

    private lateinit var etMSSV: EditText
    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var radioGroupGender: RadioGroup
    private lateinit var calendarView: CalendarView
    private lateinit var tvSelectedDate: TextView
    private lateinit var spinnerWard: Spinner
    private lateinit var spinnerDistrict: Spinner
    private lateinit var spinnerCity: Spinner
    private lateinit var cbSports: CheckBox
    private lateinit var cbMovies: CheckBox
    private lateinit var cbMusic: CheckBox
    private lateinit var cbAgree: CheckBox
    private lateinit var btnSubmit: Button
    private lateinit var btnToggleCalendar: Button
    private var selectedDate: String = ""
    private lateinit var addressHelper: AddressHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b3)


        etMSSV = findViewById(R.id.etMSSV)
        etName = findViewById(R.id.etName)
        radioGroupGender = findViewById(R.id.radioGroupGender)
        etEmail = findViewById(R.id.etEmail)
        etPhone = findViewById(R.id.etPhone)
        calendarView = findViewById(R.id.calendarView)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        spinnerWard = findViewById(R.id.spinnerWard)
        spinnerDistrict = findViewById(R.id.spinnerDistrict)
        spinnerCity = findViewById(R.id.spinnerCity)
        cbSports = findViewById(R.id.cbSports)
        cbMovies = findViewById(R.id.cbMovies)
        cbMusic = findViewById(R.id.cbMusic)
        cbAgree = findViewById(R.id.cbAgree)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnToggleCalendar = findViewById(R.id.btnToggleCalendar)


        addressHelper = AddressHelper(this)


        val provinces = addressHelper.getProvinces()
        val adapterCity = ArrayAdapter(this, android.R.layout.simple_spinner_item, provinces)
        adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCity.adapter = adapterCity


        spinnerCity.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedProvince = provinces[position]
                val districts = addressHelper.getDistricts(selectedProvince)
                val adapterDistrict = ArrayAdapter(this@Bai3Controller, android.R.layout.simple_spinner_item, districts)
                adapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerDistrict.adapter = adapterDistrict
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        })


        spinnerDistrict.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedDistrict = spinnerDistrict.selectedItem.toString()
                val wards = addressHelper.getWards(spinnerCity.selectedItem.toString(), selectedDistrict)
                val adapterWard = ArrayAdapter(this@Bai3Controller, android.R.layout.simple_spinner_item, wards)
                adapterWard.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerWard.adapter = adapterWard
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        })


        btnToggleCalendar.setOnClickListener {
            calendarView.visibility = if (calendarView.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }


        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = "$dayOfMonth/${month + 1}/$year"
            tvSelectedDate.text = "Ngày sinh: $selectedDate"
            calendarView.visibility = View.GONE
        }


        btnSubmit.setOnClickListener { validateAndSubmit() }
    }

    private fun validateAndSubmit() {

        val mssv = etMSSV.text.toString().trim()
        val name = etName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val phone = etPhone.text.toString().trim()
        val genderSelected = radioGroupGender.checkedRadioButtonId != -1
        val agreeChecked = cbAgree.isChecked

        if (mssv.isEmpty() || name.isEmpty() || email.isEmpty() || phone.isEmpty() || !genderSelected || selectedDate.isEmpty() || !agreeChecked) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin và đồng ý điều khoản", Toast.LENGTH_LONG).show()
            return
        }


        Toast.makeText(this, "Thông tin đã được gửi thành công!", Toast.LENGTH_SHORT).show()
    }
}
