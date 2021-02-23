package lexi.frontends.kotlin.transformations.ast

import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}
import lexi.frontends.kotlin.{AST, KtDeclaration}

import scala.util.Try

object DeclarationContext extends KotlinParserBaseVisitor[Option[AST] => KtDeclaration] {
  override def visitDeclaration(ctx: KotlinParser.DeclarationContext) = { parentNode =>
    new KtDeclaration {
      parent = parentNode
      context = Some(ctx)
      classDeclaration = Try(
        ClassDeclarationContext.visit(ctx.classDeclaration)(Some(this))
      ).toOption
      propertyDeclaration = Try(
        PropertyDeclarationContext.visit(ctx.propertyDeclaration)(Some(this))
      ).toOption
      functionDeclaration = Try(
        FunctionDeclarationContext.visit(ctx.functionDeclaration)(Some(this))
      ).toOption
    }
  }
}