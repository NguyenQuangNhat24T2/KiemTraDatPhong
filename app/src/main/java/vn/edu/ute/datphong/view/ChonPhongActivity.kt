package vn.edu.ute.datphong.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import vn.edu.ute.datphong.data.DuLieuMau
import vn.edu.ute.datphong.databinding.ActivityChonPhongBinding
import vn.edu.ute.datphong.helper.DatPhongHelper
import vn.edu.ute.datphong.helper.dinhDangTien
import vn.edu.ute.datphong.model.LoaiPhong

class ChonPhongActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChonPhongBinding
    private var danhSachPhong: List<LoaiPhong> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChonPhongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val soKhach = intent.getIntExtra("so_khach", 1)
        danhSachPhong = DatPhongHelper.locTheoSoKhach(DuLieuMau.dsLoaiPhong, soKhach)
        if (danhSachPhong.isEmpty()) {
            danhSachPhong = DuLieuMau.dsLoaiPhong
        }

        danhSachPhong.forEachIndexed { index, lp ->
            val rb = RadioButton(this).apply {
                id = index
                text = "${lp.ten} - Sức chứa: ${lp.sucChua} - Giá: ${lp.giaMotDem.dinhDangTien()}"
            }
            binding.rgLoaiPhong.addView(rb)
        }

        binding.btnChonXong.setOnClickListener {
            val checkedId = binding.rgLoaiPhong.checkedRadioButtonId
            if (checkedId == -1) {
                Toast.makeText(this, "Vui lòng chọn loại phòng", Toast.LENGTH_SHORT).show()
            } else {
                val chon = danhSachPhong[checkedId]
                val resultIntent = Intent().apply {
                    putExtra("ma_loai_phong", chon.ma)
                }
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}