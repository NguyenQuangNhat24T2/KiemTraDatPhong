package vn.edu.ute.datphong.data
import vn.edu.ute.datphong.model.LoaiPhong

object DuLieuMau {
    val dsLoaiPhong: List<LoaiPhong> = listOf(
        LoaiPhong(ma = "P226", ten = "Phong Standard 226",giaMotDem = 500000L, sucChua = 2),
        LoaiPhong(ma = "P101", ten = "Phong Superrior",giaMotDem = 800000L, sucChua = 3),
        LoaiPhong(ma = "P202", ten = "Phong Deluxe",giaMotDem = 1200000L, sucChua = 4),
        LoaiPhong(ma = "P303", ten = "Phong Suite Vip",giaMotDem = 2500000L, sucChua = 6),
    )
}
