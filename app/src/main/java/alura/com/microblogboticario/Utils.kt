package alura.com.microblogboticario

import java.lang.StringBuilder

class Utils {

    companion object {

        fun formatDate(data: String): String {
            var result = StringBuilder()

            result.append(data.subSequence(11, 13))
            result.append("h")
            result.append(data.subSequence(14, 16))
            result.append(" - ")
            result.append(data.subSequence(8, 10))
            result.append("/")
            result.append(data.subSequence(5, 7))
            result.append("/")
            result.append(data.subSequence(0, 4))

            return result.toString()
        }
    }


}