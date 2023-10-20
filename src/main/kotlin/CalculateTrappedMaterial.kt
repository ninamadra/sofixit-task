/**
 * Calculates the amount of trapped material between the walls formed by the terrain heights and holes.
 *
 * @param spaceship An array of terrain heights represented by integers.
 * @return The volume of trapped material between the terrain heights and in holes.
 */
fun material(spaceship: IntArray): Int {
    var trappedMaterial = 0
    var left = 0
    var right = spaceship.size - 1
    var leftMax = 0
    var rightMax = 0

    while (left < right) {
        if (spaceship[left] <= spaceship[right]) {
            if (spaceship[left] >= leftMax) {
                leftMax = spaceship[left]
            } else {
                trappedMaterial += leftMax - spaceship[left]
            }
            left++
        } else {
            if (spaceship[right] >= rightMax) {
                rightMax = spaceship[right]
            } else {
                trappedMaterial += rightMax - spaceship[right]
            }
            right--
        }
    }

    return trappedMaterial
}

