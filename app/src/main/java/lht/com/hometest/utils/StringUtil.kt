package lht.com.hometest.utils

class StringUtil {
    companion object {
        /**
         * If the keyword is more than one word, then display in two lines.
         * These two lines should have minimum difference in length.
         * For example: "nguyễn nhật ánh" should be "nguyễn\nnhật ánh", not "nguyễn nhật\nánh".
         * Because difference in length of "nguyễn" and "nhật ánh" is less than difference in length of "nguyễn nhật" and "ánh".
         */
        @Synchronized
        fun breakKeyword(keyword: String?): String? {
            if (keyword == null || keyword.trim().isEmpty()) return keyword

            val input = keyword.trim()

            //Contain index of space character in list
            val spacePositions: ArrayList<Int> = ArrayList()

            //Find all indexes of space characters in keywords
            var idx: Int
            var start = 0
            while (true) {
                idx = input.indexOf(" ", start, true)
                if (idx == -1) {
                    break
                } else {
                    spacePositions.add(idx)
                    start = idx + 1
                    if (start >= input.length) {
                        break
                    }
                }
            }

            return when (spacePositions.size) {
                //One word
                0 -> input

                //Two words
                1 -> input.replace(" ", "\n")

                //3 words and above
                else -> {
                    val size = spacePositions.size
                    var mid = if(size == 2) 0 else size/2

                    var left: Int
                    var right: Int
                    var diffLeft: Int
                    var diffMid: Int
                    var diffRight: Int
                    val spaceIndex: Int

                    while (true) {
                        left = if (mid == 0) mid else mid - 1
                        right = if (mid == spacePositions.size - 1) mid else mid + 1

                        diffLeft = diff(input, spacePositions[left])
                        diffMid = diff(input, spacePositions[mid])
                        diffRight = diff(input, spacePositions[right])

                        if (diffMid <= diffLeft && diffMid <= diffRight) {
                            spaceIndex = spacePositions[mid]
                            break
                        } else if(diffMid > diffLeft){
                            mid =  Math.max(mid - 1, 0)
                        }else{
                            mid = Math.min(mid +1, spacePositions.size + 1)
                        }
                    }
                    StringBuilder(input.substring(0, spaceIndex)).append("\n").append(input.substring(spaceIndex + 1))
                        .toString()
                }
            }
        }

        /**
         * Calculate length difference if break string into 2 lines
         * @param input: String to break
         * @param pos: position of space charter for breaking
         */
        private fun diff(input: String, pos: Int): Int {
            return Math.abs(input.substring(0, pos).length - input.substring(pos + 1, input.length).length)
        }
    }
}