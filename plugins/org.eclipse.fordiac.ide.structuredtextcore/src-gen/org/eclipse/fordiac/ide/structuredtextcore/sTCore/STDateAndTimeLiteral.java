/**
 * generated by Xtext 2.25.0
 */
package org.eclipse.fordiac.ide.structuredtextcore.sTCore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ST Date And Time Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.fordiac.ide.structuredtextcore.sTCore.STDateAndTimeLiteral#getTimeLiteral <em>Time Literal</em>}</li>
 * </ul>
 *
 * @see org.eclipse.fordiac.ide.structuredtextcore.sTCore.STCorePackage#getSTDateAndTimeLiteral()
 * @model
 * @generated
 */
public interface STDateAndTimeLiteral extends STExpression
{
  /**
   * Returns the value of the '<em><b>Time Literal</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Time Literal</em>' containment reference.
   * @see #setTimeLiteral(DATE_AND_TIME_LITERAL)
   * @see org.eclipse.fordiac.ide.structuredtextcore.sTCore.STCorePackage#getSTDateAndTimeLiteral_TimeLiteral()
   * @model containment="true"
   * @generated
   */
  DATE_AND_TIME_LITERAL getTimeLiteral();

  /**
   * Sets the value of the '{@link org.eclipse.fordiac.ide.structuredtextcore.sTCore.STDateAndTimeLiteral#getTimeLiteral <em>Time Literal</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Time Literal</em>' containment reference.
   * @see #getTimeLiteral()
   * @generated
   */
  void setTimeLiteral(DATE_AND_TIME_LITERAL value);

} // STDateAndTimeLiteral
