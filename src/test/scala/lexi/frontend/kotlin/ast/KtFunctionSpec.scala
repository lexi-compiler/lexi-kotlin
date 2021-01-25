package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.phases.Phase.parse
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class KtFunctionSpec extends AnyFunSpec with Matchers {
  private def node(ast: ASTNode): KtFunction =
    ast
      .asInstanceOf[KtFile]
      .topLevelObjects
      .head
      .declaration
      .functionDeclaration

  describe("parse") {
    describe("expression function without parameters") {
      val source = """fun hello(): String = "Hello Maki!""""
      val ast = parse(source)

      it("parses a KtFunction") {
        node(ast) shouldBe KtFunction(
          name = "hello",
          `type` = "String",
          functionBody = KtFunctionBody(
            expression = KtExpression(
              KtDisjunction(Vector(KtConjunction(Vector(KtEquality()))))
            )
          )
        )
      }
    }

    describe("expression function with parameters") {
      val source = """fun hello(name: String): String = "Hello ${name}""""
      val ast = node(parse(source))

      it("parses a KtFunction") {
        ast shouldBe KtFunction(
          name = "hello",
          `type` = "String",
          functionBody = KtFunctionBody(
            expression = KtExpression(
              KtDisjunction(Vector(KtConjunction(Vector(KtEquality()))))
            )
          )
        )
      }
    }

    //    describe("block body function with no params and assignment in body") {
    //      val source =
    //        """fun f(): Int {
    //          |  val x = 5
    //          |  return x
    //          |}
    //          |""".stripMargin
    //      val ast = Parser(source)
    //
    //      it("parses the function") {
    //        ast.children.head should have(
    //          Symbol("name")("f"),
    //          Symbol("type")("Int")
    //        )
    //      }
    //
    //      it("parses the block body") {
    //        ast.children.head should have(
    //          Symbol("x")("5")
    //        )
    //      }
    //    }
  }
}