package lexi.ir.nodes

case class IrFunction(
  var name: String,
  var `type`: String,
  var functionBody: IrFunctionBody
) extends IrNode