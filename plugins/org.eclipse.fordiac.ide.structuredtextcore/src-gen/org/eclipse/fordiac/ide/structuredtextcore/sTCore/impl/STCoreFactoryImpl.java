/**
 * generated by Xtext 2.25.0
 */
package org.eclipse.fordiac.ide.structuredtextcore.sTCore.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.fordiac.ide.structuredtextcore.sTCore.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class STCoreFactoryImpl extends EFactoryImpl implements STCoreFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static STCoreFactory init()
  {
    try
    {
      STCoreFactory theSTCoreFactory = (STCoreFactory)EPackage.Registry.INSTANCE.getEFactory(STCorePackage.eNS_URI);
      if (theSTCoreFactory != null)
      {
        return theSTCoreFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new STCoreFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public STCoreFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case STCorePackage.CODE: return createCode();
      case STCorePackage.VAR_DECLARATION_BLOCK: return createVarDeclarationBlock();
      case STCorePackage.VAR_DECLARATION: return createVarDeclaration();
      case STCorePackage.INITIALIZER_EXPRESSION: return createInitializerExpression();
      case STCorePackage.ARRAY_INITIALIZER_EXPRESSION: return createArrayInitializerExpression();
      case STCorePackage.ARRAY_INIT_ELEMENT: return createArrayInitElement();
      case STCorePackage.ST_STATEMENTS: return createSTStatements();
      case STCorePackage.ST_ASSIGNMENT_STATEMENT: return createSTAssignmentStatement();
      case STCorePackage.ST_BRANCH_STATEMENTS: return createSTBranchStatements();
      case STCorePackage.ST_IF_STATMENT: return createSTIfStatment();
      case STCorePackage.ST_ELSE_IF_PART: return createSTElseIfPart();
      case STCorePackage.ST_CASE_STATEMENT: return createSTCaseStatement();
      case STCorePackage.ST_CASE_CASES: return createSTCaseCases();
      case STCorePackage.ST_ELSE_PART: return createSTElsePart();
      case STCorePackage.ST_LOOP_STATEMENTS: return createSTLoopStatements();
      case STCorePackage.ST_FOR_STATEMENT: return createSTForStatement();
      case STCorePackage.ST_WHILE_STATEMENT: return createSTWhileStatement();
      case STCorePackage.ST_REPEAT_STATEMENT: return createSTRepeatStatement();
      case STCorePackage.ST_EXPRESSION: return createSTExpression();
      case STCorePackage.MULTIBIT_PARTIAL_ACCESS: return createMultibitPartialAccess();
      case STCorePackage.NUMERIC_LITERAL: return createNUMERIC_LITERAL();
      case STCorePackage.DATE_LITERAL: return createDATE_LITERAL();
      case STCorePackage.TIME_LITERAL: return createTIME_LITERAL();
      case STCorePackage.TIME_OF_DAY_LITERAL: return createTIME_OF_DAY_LITERAL();
      case STCorePackage.DATE_AND_TIME_LITERAL: return createDATE_AND_TIME_LITERAL();
      case STCorePackage.STRING_LITERAL: return createSTRING_LITERAL();
      case STCorePackage.ST_SUBRANGE_EXPRESSION: return createSTSubrangeExpression();
      case STCorePackage.ST_OR_EXPRESSION: return createSTOrExpression();
      case STCorePackage.ST_XOR_EXPRESSION: return createSTXorExpression();
      case STCorePackage.ST_AND_EXPRESSION: return createSTAndExpression();
      case STCorePackage.ST_EQUALITY_EXPRESSION: return createSTEqualityExpression();
      case STCorePackage.ST_COMPARISON_EXPRESSION: return createSTComparisonExpression();
      case STCorePackage.ST_ADD_SUB_EXPRESSION: return createSTAddSubExpression();
      case STCorePackage.ST_MUL_DIV_MOD_EXPRESSION: return createSTMulDivModExpression();
      case STCorePackage.ST_POWER_EXPRESSION: return createSTPowerExpression();
      case STCorePackage.ST_SIGNUM_EXPRESSION: return createSTSignumExpression();
      case STCorePackage.ST_MEMBER_SELECTION: return createSTMemberSelection();
      case STCorePackage.ST_SYMBOL: return createSTSymbol();
      case STCorePackage.ST_RETURN: return createSTReturn();
      case STCorePackage.ST_CONTINUE: return createSTContinue();
      case STCorePackage.ST_EXIT: return createSTExit();
      case STCorePackage.ST_NUMERIC_LITERAL: return createSTNumericLiteral();
      case STCorePackage.ST_DATE_LITERAL: return createSTDateLiteral();
      case STCorePackage.ST_TIME_LITERAL: return createSTTimeLiteral();
      case STCorePackage.ST_TIME_OF_DAY_LITERAL: return createSTTimeOfDayLiteral();
      case STCorePackage.ST_DATE_AND_TIME_LITERAL: return createSTDateAndTimeLiteral();
      case STCorePackage.ST_STRING_LITERAL: return createSTStringLiteral();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case STCorePackage.MULTI_BIT_ACCESS_SPECIFIER:
        return createMultiBitAccessSpecifierFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case STCorePackage.MULTI_BIT_ACCESS_SPECIFIER:
        return convertMultiBitAccessSpecifierToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Code createCode()
  {
    CodeImpl code = new CodeImpl();
    return code;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public VarDeclarationBlock createVarDeclarationBlock()
  {
    VarDeclarationBlockImpl varDeclarationBlock = new VarDeclarationBlockImpl();
    return varDeclarationBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public VarDeclaration createVarDeclaration()
  {
    VarDeclarationImpl varDeclaration = new VarDeclarationImpl();
    return varDeclaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public InitializerExpression createInitializerExpression()
  {
    InitializerExpressionImpl initializerExpression = new InitializerExpressionImpl();
    return initializerExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ArrayInitializerExpression createArrayInitializerExpression()
  {
    ArrayInitializerExpressionImpl arrayInitializerExpression = new ArrayInitializerExpressionImpl();
    return arrayInitializerExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ArrayInitElement createArrayInitElement()
  {
    ArrayInitElementImpl arrayInitElement = new ArrayInitElementImpl();
    return arrayInitElement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STStatements createSTStatements()
  {
    STStatementsImpl stStatements = new STStatementsImpl();
    return stStatements;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STAssignmentStatement createSTAssignmentStatement()
  {
    STAssignmentStatementImpl stAssignmentStatement = new STAssignmentStatementImpl();
    return stAssignmentStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STBranchStatements createSTBranchStatements()
  {
    STBranchStatementsImpl stBranchStatements = new STBranchStatementsImpl();
    return stBranchStatements;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STIfStatment createSTIfStatment()
  {
    STIfStatmentImpl stIfStatment = new STIfStatmentImpl();
    return stIfStatment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STElseIfPart createSTElseIfPart()
  {
    STElseIfPartImpl stElseIfPart = new STElseIfPartImpl();
    return stElseIfPart;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STCaseStatement createSTCaseStatement()
  {
    STCaseStatementImpl stCaseStatement = new STCaseStatementImpl();
    return stCaseStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STCaseCases createSTCaseCases()
  {
    STCaseCasesImpl stCaseCases = new STCaseCasesImpl();
    return stCaseCases;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STElsePart createSTElsePart()
  {
    STElsePartImpl stElsePart = new STElsePartImpl();
    return stElsePart;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STLoopStatements createSTLoopStatements()
  {
    STLoopStatementsImpl stLoopStatements = new STLoopStatementsImpl();
    return stLoopStatements;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STForStatement createSTForStatement()
  {
    STForStatementImpl stForStatement = new STForStatementImpl();
    return stForStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STWhileStatement createSTWhileStatement()
  {
    STWhileStatementImpl stWhileStatement = new STWhileStatementImpl();
    return stWhileStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STRepeatStatement createSTRepeatStatement()
  {
    STRepeatStatementImpl stRepeatStatement = new STRepeatStatementImpl();
    return stRepeatStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STExpression createSTExpression()
  {
    STExpressionImpl stExpression = new STExpressionImpl();
    return stExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public MultibitPartialAccess createMultibitPartialAccess()
  {
    MultibitPartialAccessImpl multibitPartialAccess = new MultibitPartialAccessImpl();
    return multibitPartialAccess;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NUMERIC_LITERAL createNUMERIC_LITERAL()
  {
    NUMERIC_LITERALImpl numeriC_LITERAL = new NUMERIC_LITERALImpl();
    return numeriC_LITERAL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public DATE_LITERAL createDATE_LITERAL()
  {
    DATE_LITERALImpl datE_LITERAL = new DATE_LITERALImpl();
    return datE_LITERAL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public TIME_LITERAL createTIME_LITERAL()
  {
    TIME_LITERALImpl timE_LITERAL = new TIME_LITERALImpl();
    return timE_LITERAL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public TIME_OF_DAY_LITERAL createTIME_OF_DAY_LITERAL()
  {
    TIME_OF_DAY_LITERALImpl timE_OF_DAY_LITERAL = new TIME_OF_DAY_LITERALImpl();
    return timE_OF_DAY_LITERAL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public DATE_AND_TIME_LITERAL createDATE_AND_TIME_LITERAL()
  {
    DATE_AND_TIME_LITERALImpl datE_AND_TIME_LITERAL = new DATE_AND_TIME_LITERALImpl();
    return datE_AND_TIME_LITERAL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STRING_LITERAL createSTRING_LITERAL()
  {
    STRING_LITERALImpl strinG_LITERAL = new STRING_LITERALImpl();
    return strinG_LITERAL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STSubrangeExpression createSTSubrangeExpression()
  {
    STSubrangeExpressionImpl stSubrangeExpression = new STSubrangeExpressionImpl();
    return stSubrangeExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STOrExpression createSTOrExpression()
  {
    STOrExpressionImpl stOrExpression = new STOrExpressionImpl();
    return stOrExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STXorExpression createSTXorExpression()
  {
    STXorExpressionImpl stXorExpression = new STXorExpressionImpl();
    return stXorExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STAndExpression createSTAndExpression()
  {
    STAndExpressionImpl stAndExpression = new STAndExpressionImpl();
    return stAndExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STEqualityExpression createSTEqualityExpression()
  {
    STEqualityExpressionImpl stEqualityExpression = new STEqualityExpressionImpl();
    return stEqualityExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STComparisonExpression createSTComparisonExpression()
  {
    STComparisonExpressionImpl stComparisonExpression = new STComparisonExpressionImpl();
    return stComparisonExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STAddSubExpression createSTAddSubExpression()
  {
    STAddSubExpressionImpl stAddSubExpression = new STAddSubExpressionImpl();
    return stAddSubExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STMulDivModExpression createSTMulDivModExpression()
  {
    STMulDivModExpressionImpl stMulDivModExpression = new STMulDivModExpressionImpl();
    return stMulDivModExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STPowerExpression createSTPowerExpression()
  {
    STPowerExpressionImpl stPowerExpression = new STPowerExpressionImpl();
    return stPowerExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STSignumExpression createSTSignumExpression()
  {
    STSignumExpressionImpl stSignumExpression = new STSignumExpressionImpl();
    return stSignumExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STMemberSelection createSTMemberSelection()
  {
    STMemberSelectionImpl stMemberSelection = new STMemberSelectionImpl();
    return stMemberSelection;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STSymbol createSTSymbol()
  {
    STSymbolImpl stSymbol = new STSymbolImpl();
    return stSymbol;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STReturn createSTReturn()
  {
    STReturnImpl stReturn = new STReturnImpl();
    return stReturn;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STContinue createSTContinue()
  {
    STContinueImpl stContinue = new STContinueImpl();
    return stContinue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STExit createSTExit()
  {
    STExitImpl stExit = new STExitImpl();
    return stExit;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STNumericLiteral createSTNumericLiteral()
  {
    STNumericLiteralImpl stNumericLiteral = new STNumericLiteralImpl();
    return stNumericLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STDateLiteral createSTDateLiteral()
  {
    STDateLiteralImpl stDateLiteral = new STDateLiteralImpl();
    return stDateLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STTimeLiteral createSTTimeLiteral()
  {
    STTimeLiteralImpl stTimeLiteral = new STTimeLiteralImpl();
    return stTimeLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STTimeOfDayLiteral createSTTimeOfDayLiteral()
  {
    STTimeOfDayLiteralImpl stTimeOfDayLiteral = new STTimeOfDayLiteralImpl();
    return stTimeOfDayLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STDateAndTimeLiteral createSTDateAndTimeLiteral()
  {
    STDateAndTimeLiteralImpl stDateAndTimeLiteral = new STDateAndTimeLiteralImpl();
    return stDateAndTimeLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STStringLiteral createSTStringLiteral()
  {
    STStringLiteralImpl stStringLiteral = new STStringLiteralImpl();
    return stStringLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MultiBitAccessSpecifier createMultiBitAccessSpecifierFromString(EDataType eDataType, String initialValue)
  {
    MultiBitAccessSpecifier result = MultiBitAccessSpecifier.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertMultiBitAccessSpecifierToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public STCorePackage getSTCorePackage()
  {
    return (STCorePackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static STCorePackage getPackage()
  {
    return STCorePackage.eINSTANCE;
  }

} //STCoreFactoryImpl
