/**
 * generated by Xtext 2.25.0
 */
package org.eclipse.fordiac.ide.structuredtextcore.sTCore.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.fordiac.ide.structuredtextcore.sTCore.NUMERIC_LITERAL;
import org.eclipse.fordiac.ide.structuredtextcore.sTCore.STCorePackage;
import org.eclipse.fordiac.ide.structuredtextcore.sTCore.STNumericLiteral;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ST Numeric Literal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.fordiac.ide.structuredtextcore.sTCore.impl.STNumericLiteralImpl#getNumericLiteral <em>Numeric Literal</em>}</li>
 * </ul>
 *
 * @generated
 */
public class STNumericLiteralImpl extends STExpressionImpl implements STNumericLiteral
{
  /**
   * The cached value of the '{@link #getNumericLiteral() <em>Numeric Literal</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNumericLiteral()
   * @generated
   * @ordered
   */
  protected NUMERIC_LITERAL numericLiteral;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected STNumericLiteralImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return STCorePackage.Literals.ST_NUMERIC_LITERAL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NUMERIC_LITERAL getNumericLiteral()
  {
    return numericLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetNumericLiteral(NUMERIC_LITERAL newNumericLiteral, NotificationChain msgs)
  {
    NUMERIC_LITERAL oldNumericLiteral = numericLiteral;
    numericLiteral = newNumericLiteral;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, STCorePackage.ST_NUMERIC_LITERAL__NUMERIC_LITERAL, oldNumericLiteral, newNumericLiteral);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setNumericLiteral(NUMERIC_LITERAL newNumericLiteral)
  {
    if (newNumericLiteral != numericLiteral)
    {
      NotificationChain msgs = null;
      if (numericLiteral != null)
        msgs = ((InternalEObject)numericLiteral).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - STCorePackage.ST_NUMERIC_LITERAL__NUMERIC_LITERAL, null, msgs);
      if (newNumericLiteral != null)
        msgs = ((InternalEObject)newNumericLiteral).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - STCorePackage.ST_NUMERIC_LITERAL__NUMERIC_LITERAL, null, msgs);
      msgs = basicSetNumericLiteral(newNumericLiteral, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, STCorePackage.ST_NUMERIC_LITERAL__NUMERIC_LITERAL, newNumericLiteral, newNumericLiteral));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case STCorePackage.ST_NUMERIC_LITERAL__NUMERIC_LITERAL:
        return basicSetNumericLiteral(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case STCorePackage.ST_NUMERIC_LITERAL__NUMERIC_LITERAL:
        return getNumericLiteral();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case STCorePackage.ST_NUMERIC_LITERAL__NUMERIC_LITERAL:
        setNumericLiteral((NUMERIC_LITERAL)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case STCorePackage.ST_NUMERIC_LITERAL__NUMERIC_LITERAL:
        setNumericLiteral((NUMERIC_LITERAL)null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case STCorePackage.ST_NUMERIC_LITERAL__NUMERIC_LITERAL:
        return numericLiteral != null;
    }
    return super.eIsSet(featureID);
  }

} //STNumericLiteralImpl
