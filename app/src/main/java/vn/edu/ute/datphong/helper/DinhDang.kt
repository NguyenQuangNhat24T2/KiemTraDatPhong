package vn.edu.ute.datphong.helper
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun Long.dinhDangTien(): String {
    val symbols = DecimalFormatSymbols(Locale.getDefault()).apply {
        groupingSeparator = '.'
    }
    val formatter = DecimalFormat("#,###", symbols)
    return "${formatter.format(this)} đ"
}