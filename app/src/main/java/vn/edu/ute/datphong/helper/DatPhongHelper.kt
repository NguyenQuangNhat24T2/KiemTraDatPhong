package vn.edu.ute.datphong.helper
import vn.edu.ute.datphong.data.DuLieuMau
import vn.edu.ute.datphong.model.LoaiPhong

object DatPhongHelper {
    fun timLoaiPhong(ma: String): LoaiPhong? {
        val maChuanHoa = ma.trim()
        return DuLieuMau.dsLoaiPhong.find { it.ma.equals(maChuanHoa, ignoreCase = true) }
    }
    fun hopLeSoKhach(loai: LoaiPhong, soKhach: Int): Boolean {
        return soKhach in 1..(loai.sucChua +2)
    }
    fun tinhTienPhong_2415053122226(loai: LoaiPhong, soDem: Int, soKhach: Int): Long {
        if (soDem < 1 || !hopLeSoKhach(loai, soKhach)){
            return 0L
        }
        var tienPhong = loai.giaMotDem * soDem
        if (soDem >= 4) {
            tienPhong = (tienPhong *90) / 100
        }
        val soKhachVuot = if (soKhach > loai.sucChua) soKhach - loai.sucChua else 0
        val phuThu = soKhachVuot.toLong() * 150000L * soDem.toLong()
        return tienPhong + phuThu
    }
    fun taoMaDon(don: DonDatPhong): String {
        val parts = don.ngayNhan.split("/")
        val ddMM = if (parts.size >= 2) "${parts[0]}${parts[1]}" else "0101"
        return "DP226-$ddMM-${don.maLoaiPhong.uppercase()}"
    }

    fun locTheoSoKhach(ds: List<LoaiPhong>, soKhach: Int): List<LoaiPhong> {
        return ds.filter { it.sucChua >= soKhach }
            .sortedBy { it.giaMotDem }
    }
}