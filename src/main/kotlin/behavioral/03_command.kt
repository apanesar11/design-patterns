interface OrderCommand {
    fun execute()
}

class OrderAddCommand(private val orderId: Long): OrderCommand {
    override fun execute() {
        println("Adding an order for id = $orderId")
    }
}

class OrderPayCommand(private val orderId: Long): OrderCommand {
    override fun execute() {
        println("Paying for order id = $orderId")
    }
}

class CommandProcessor {
    private val commandQueue = ArrayList<OrderCommand>()

    fun addToQueue(command: OrderCommand): CommandProcessor =
        apply {
            commandQueue.add(command)
        }

    fun processCommands(): CommandProcessor =
        apply {
            commandQueue.forEach { it.execute() }
            commandQueue.clear()
        }
}

fun main() {
    CommandProcessor()
        .addToQueue(OrderAddCommand(1L))
        .addToQueue(OrderAddCommand(2L))
        .addToQueue(OrderPayCommand(1L))
        .addToQueue(OrderPayCommand(2L))
        .processCommands()
}