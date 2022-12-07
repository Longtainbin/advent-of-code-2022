package day07

/* 节点定义 */
data class Node(
    val name: String,
    val isDirectory: Boolean,
    var size: Long = 0,
    var fileChildren: MutableList<Node>? = null,
    var dirChildren: MutableList<Node>? = null,
    var parent: Node? = null
)

fun Node.addChild(child: Node) {
    if (child.isDirectory) {
        if (this.dirChildren == null) {
            this.dirChildren = mutableListOf()
        }
        this.dirChildren!!.add(child)
    } else {
        if (this.fileChildren == null) {
            this.fileChildren = mutableListOf()
        }
        this.fileChildren!!.add(child)
    }
    child.parent = this
}

fun Node.findChild(childName: String): Node {
    val temp = this.dirChildren?.let {
        it.first { node -> node.isDirectory && node.name == childName }
    }
    return temp?:this
}
