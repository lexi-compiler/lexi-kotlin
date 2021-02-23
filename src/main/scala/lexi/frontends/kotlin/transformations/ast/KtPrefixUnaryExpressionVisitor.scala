package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}
import lexi.frontends.kotlin.{AST, KtPrefixUnaryExpression}

import scala.util.Try

object KtPrefixUnaryExpressionVisitor
  extends KotlinParserBaseVisitor[Option[AST] => KtPrefixUnaryExpression] {
  override def visitPrefixUnaryExpression(
    ctx: KotlinParser.PrefixUnaryExpressionContext
  ) = { parentNode =>
    new KtPrefixUnaryExpression {
      parent = parentNode
      context = Some(ctx)
      postfixUnaryExpression = Try(
        KtPostfixUnaryExpressionVisitor.visit(ctx.postfixUnaryExpression)(Some(this))
      ).toOption
    }
  }
}
