package vn.edu.ute.datphong.view
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.snackbar.Snackbar
import vn.edu.ute.datphong.data.DuLieuMau
import vn.edu.ute.datphong.databinding.ActivityDatPhongBinding
import vn.edu.ute.datphong.helper.DatPhongHelper
import vn.edu.ute.datphong.helper.KiemTraHelper
import vn.edu.ute.datphong.model.LoaiPhong

class DatPhongActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDatPhongBinding
    private var loaiPhongHienTai: LoaiPhong = DuLieuMau.dsLoaiPhong[0]
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatPhongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        capNhatHienThiLoaiPhong()
        thietLapSuKienTamTinh()
        thietLapSuKienDatPhong()
    }
    private fun capNhatHienThiLoaiPhong() {
        binding.txtLoaiPhong.text = "Loại phòng: ${loaiPhongHienTai.ten} - ${loaiPhongHienTai.giaMotDem} đ/đêm (Tối đa ${loaiPhongHienTai.sucChua} người)"
        capNhatTamTinh()
    }
    private fun thietLapSuKienTamTinh() {
        binding.edtSoDem.doAfterTextChanged {
            capNhatTamTinh()
        }
        binding.edtSoKhach.doAfterTextChanged {
            capNhatTamTinh()
        }
    }
    private fun capNhatTamTinh() {
        val soDem = binding.edtSoDem.text.toString().trim().toIntOrNull()
        val soKhach = binding.edtSoKhach.text.toString().trim().toIntOrNull()
        if (soDem != null && soKhach != null) {
            val tien = DatPhongHelper.tinhTienPhong_2415053122226(loaiPhongHienTai, soDem, soKhach)
            if (tien > 0L) {
                binding.txtTamTinh.text = "Tạm tính: $tien"
            } else {
                binding.txtTamTinh.text = "Tạm tính: 0"
            }
        } else {
            binding.txtTamTinh.text = "Tạm tính: 0"
        }
    }
    private fun thietLapSuKienDatPhong() {
        binding.btnDatPhong.setOnClickListener {
            // Xóa lỗi cũ
            binding.tilHoTen.error = null
            binding.tilSdt.error = null
            binding.tilEmail.error = null
            binding.tilNgayNhan.error = null
            binding.tilSoDem.error = null
            binding.tilSoKhach.error = null
            val hoTen = binding.edtHoTen.text.toString().trim()
            val sdt = binding.edtSdt.text.toString().trim()
            val email = binding.edtEmail.text.toString().trim()
            val ngayNhan = binding.edtNgayNhan.text.toString().trim()
            val soDemStr = binding.edtSoDem.text.toString().trim()
            val soKhachStr = binding.edtSoKhach.text.toString().trim()
            var hopLe = true
            if (!KiemTraHelper.kiemTraHoTen(hoTen)) {
                binding.tilHoTen.error = "Họ tên không đúng định dạng(ít nhất 2 từ)"
                hopLe = false
            }
            if (!KiemTraHelper.kiemTraSdt(sdt)) {
                binding.tilSdt.error = "Số điện thoại gồm 10 số,bắt đầu bằng 07 hoặc 08"
                hopLe = false
            }
            if (!KiemTraHelper.kiemTraEmail(email)) {
                binding.tilEmail.error = "Email phải đúng đuôi @gmail.com"
                hopLe = false
            }
            if (!KiemTraHelper.kiemTraNgay(ngayNhan)) {
                binding.tilNgayNhan.error = "Ngày nhận dd/MM/yyyy (2026 - 2029)"
                hopLe = false
            }
            val soDem = soDemStr.toIntOrNull()
            if (soDem == null || soDem !in 1..16) {
                binding.tilSoDem.error = "Số đêm phải từ 1 đến 16"
                hopLe = false
            }
            val soKhach = soKhachStr.toIntOrNull()
            if (soKhach == null || !DatPhongHelper.hopLeSoKhach(loaiPhongHienTai, soKhach)) {
                binding.tilSoKhach.error = "Số khách không hợp lệ với loại phòng này"
                hopLe = false
            }
            if (!hopLe) {
                Snackbar.make(binding.root, "Vui lòng kiểm tra lại thông tin", Snackbar.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Đã ghi nhận đơn đặt phòng của $hoTen", Toast.LENGTH_SHORT).show()
            }
        }
    }
}