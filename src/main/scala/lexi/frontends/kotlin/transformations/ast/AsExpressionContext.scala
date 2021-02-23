package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}
import lexi.frontends.kotlin.{AST, KtAsExpression}

import scala.util.Try

object AsExpressionContext extends KotlinParserBaseVisitor[Option[AST] => KtAsExpression] {
  override def visitAsExpression(ctx: KotlinParser.AsExpressionContext) =
    parentNode =>
      new KtAsExpression {
        parent = parentNode
        context = Some(ctx)
        prefixUnaryExpression = Try(
          PrefixUnaryExpressionContext.visit(ctx.prefixUnaryExpression)(Some(this))
        ).toOption
      }
}