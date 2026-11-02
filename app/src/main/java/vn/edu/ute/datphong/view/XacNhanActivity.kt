package vn.edu.ute.datphong.view

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import vn.edu.ute.datphong.databinding.ActivityXacNhanBinding
import vn.edu.ute.datphong.helper.DatPhongHelper
import vn.edu.ute.datphong.helper.dinhDangTien
import vn.edu.ute.datphong.model.DonDatPhong

class XacNhanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityXacNhanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityXacNhanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val don = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("don_dat_phong", DonDatPhong::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("don_dat_phong") as? DonDatPhong
        }
        val tongTien = intent.getLongExtra("tong_tien", 0L)

        if (don != null) {
            binding.txtHoTen.text = "Họ tên: ${don.hoTen}"
            binding.txtSdt.text = "Số điện thoại: ${don.sdt}"
            binding.txtEmail.text = "Email: ${don.email}"

            val tenLoai = DatPhongHelper.timLoaiPhong(don.maLoaiPhong)?.ten ?: don.maLoaiPhong
            binding.txtLoaiPhong.text = "Loại phòng: $tenLoai"

            binding.txtNgayNhan.text = "Ngày nhận: ${don.ngayNhan}"
            binding.txtSoDem.text = "Số đêm: ${don.soDem}"
            binding.txtSoKhach.text = "Số khách: ${don.soKhach}"
            binding.txtTongTien.text = "Tổng tiền: ${tongTien.dinhDangTien()}"
        }

        binding.btnSua.setOnClickListener {
            finish()
        }
        binding.btnXacNhan.setOnClickListener {
            if (don != null) {
                val maDon = DatPhongHelper.taoMaDon(don)
                val data = Intent().apply {
                    putExtra("ma_don", maDon)
                }
                setResult(RESULT_OK, data)
                finish()
            }
        }
    }
}