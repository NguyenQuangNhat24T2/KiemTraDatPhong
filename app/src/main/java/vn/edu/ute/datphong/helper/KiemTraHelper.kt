package vn.edu.ute.datphong.helper

object KiemTraHelper {
    fun kiemTraHoTen(hoTen: String): Boolean {
        val regex = Regex("^[a-zA-ZÀ-ỹ]+(\\s[a-zA-ZÀ-ỹ]+)+$")
        return regex.matches(hoTen.trim())
    }
    fun kiemTraSdt(sdt: String): Boolean {
        val regex = Regex("^(07|08)\\d{8}$")
        return regex.matches(sdt.trim())
    }
    fun kiemTraEmail(email: String): Boolean {
        val regex = Regex("^[a-zA-Z][a-zA-Z0-9._]{2,29}@gmail\\.com$")
        return regex.matches(email.trim())
    }
    fun kiemTraNgay(ngay: String): Boolean {
        val regex = Regex("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(202[6-9])$")
        return regex.matches(ngay.trim())
    }
}