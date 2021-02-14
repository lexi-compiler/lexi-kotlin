package lexi.frontends.kotlin.ir

import lexi.frontends.kotlin.ast.{ASTNode, KtFile}
import lexi.ir.nodes.IrFile

import scala.util.Try

object KtFileVisitor extends KtVisitor {
  override def visit(ast: ASTNode): IrFile =
    (
      (ktFile: KtFile) =>
        new IrFile {
          topLevelObjects = ktFile.topLevelObjects.map(_.map(KtTopLevelObjectVisitor.visit(_)))
        }
    )(ast.asInstanceOf[KtFile])
}