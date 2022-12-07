package day07

/**
 * 通过dfs重新计算每个目录节点的size
 */
fun calculateDirectorySize(root: Node): Long {
    if (root.fileChildren == null && root.dirChildren == null) {
        return root.size
    }
    val fileSum = root.fileChildren?.sumOf { it.size }
    val dirSum = root.dirChildren?.sumOf { calculateDirectorySize(it) }

    root.size += (fileSum ?: 0L) + (dirSum ?: 0)
    return root.size
}


/**
 * 生成初始的树结构, 当前所有目录的大小都为0
 */
fun generateTree(input: List<String>): Node {
    val root = Node("/", true)
    var currentNode = root

    var currentIndex = 0;
    while (currentIndex < input.size) {
        if (input[currentIndex].startsWith("\$ cd")) {
            currentNode = processCd(input[currentIndex], currentNode, root)
            currentIndex++
        } else if (input[currentIndex].startsWith("\$ ls")) {
            val count = countLs(currentIndex, input)
            val start = currentIndex + 1
            val end = currentIndex + count
            for (i in start..end) {
                currentNode.addChild(createInitNode(input[i]))
            }
            currentIndex = end + 1
        }
    }
    return root
}


/* 命令行处理 */
// 返回cd 命令切换后的目录节点
fun processCd(cmd: String, currentNode: Node, root: Node): Node {
    return when (val argument = cmd.split(' ')[2]) {
        "/" -> {
            root
        }
        ".." -> {
            currentNode.parent ?: root
        }
        else -> {
            currentNode.findChild(argument)
        }
    }
}

/**
 * currentIndex: 表示$ ls 命令所在的索引位置
 * return: 该ls命令所在包含的文件和目录总个数
 */
fun countLs(currentIndex: Int, input: List<String>): Int {
    var count = 0
    var i = currentIndex + 1
    while (i < input.size && input[i][0] != '$') {
        i++
        count++
    }
    return count
}

fun createInitNode(msg: String): Node {
    val args = msg.split(' ')
    return if (args[0] == "dir") {
        Node(args[1], true)
    } else {
        Node(args[1], false, args[0].toLong())
    }
}