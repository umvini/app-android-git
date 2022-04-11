package br.com.umvini.appandroidgit.utils

object Utils {
    fun stringIsNullReturnUnderscore(string: String?): String{
        return if (string.isNullOrEmpty()){
            "-"
        } else {
            string
        }
    }

    fun intIsNullReturnUnderscore(int: Int?): String{
        return int?.toString() ?: "-"
    }
}