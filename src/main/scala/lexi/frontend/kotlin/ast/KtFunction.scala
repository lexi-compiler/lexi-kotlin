package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import java.util.Optional

case class KtFunction(
  var name: String,
  var `type`: String,
  var functionBody: KtFunctionBody
) extends ASTNode

object KtFunction extends KotlinParserBaseVisitor[ASTNode] {
  override def visitFunctionDeclaration(
    ctx: KotlinParser.FunctionDeclarationContext
  ): KtFunction =
    new KtFunction(
      name = ctx.simpleIdentifier.getText,
      `type` = Optional.ofNullable(ctx.`type`).map(_.getText).orElse(""),
      functionBody = KtFunctionBody.visitFunctionBody(ctx.functionBody)
    ) {
      context = ctx
    }
}