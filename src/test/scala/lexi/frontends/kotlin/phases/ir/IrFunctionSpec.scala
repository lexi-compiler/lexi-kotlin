package lexi.frontends.kotlin.phases.ir

import lexi.frontends.kotlin.ast.{
  KtExpressionContext,
  KtFunction,
  KtFunctionBody
}
import lexi.frontends.kotlin.phases.{Ir, LanguageAnalysis, SemanticAnalysis}
import lexi.ir.nodes._

class IrFunctionSpec extends munit.FunSuite {
  test("expression function without parameters") {
    val ast = KtFunction(
      name = Some("hello"),
      `type` = Some("String"),
      functionBody = None
    )
    val irFunction = Ir(ast)
    val expected = IrFunction(
      name = Some("hello"),
      `type` = Some("String"),
      functionBody = None
    )
    assertEquals(irFunction, expected)
  }
}