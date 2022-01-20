/**
 * generated by Xtext 2.25.0
 */
package org.eclipse.fordiac.ide.structuredtextcore.sTCore.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.fordiac.ide.structuredtextcore.sTCore.DATE_LITERAL;
import org.eclipse.fordiac.ide.structuredtextcore.sTCore.STCorePackage;
import org.eclipse.fordiac.ide.structuredtextcore.sTCore.STDateLiteral;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ST Date Literal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.fordiac.ide.structuredtextcore.sTCore.impl.STDateLiteralImpl#getDateLiteral <em>Date Literal</em>}</li>
 * </ul>
 *
 * @generated
 */
public class STDateLiteralImpl extends STExpressionImpl implements STDateLiteral
{
  /**
   * The cached value of the '{@link #getDateLiteral() <em>Date Literal</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDateLiteral()
   * @generated
   * @ordered
   */
  protected DATE_LITERAL dateLiteral;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected STDateLiteralImpl()
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
    return STCorePackage.Literals.ST_DATE_LITERAL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public DATE_LITERAL getDateLiteral()
  {
    return dateLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDateLiteral(DATE_LITERAL newDateLiteral, NotificationChain msgs)
  {
    DATE_LITERAL oldDateLiteral = dateLiteral;
    dateLiteral = newDateLiteral;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, STCorePackage.ST_DATE_LITERAL__DATE_LITERAL, oldDateLiteral, newDateLiteral);
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
  public void setDateLiteral(DATE_LITERAL newDateLiteral)
  {
    if (newDateLiteral != dateLiteral)
    {
      NotificationChain msgs = null;
      if (dateLiteral != null)
        msgs = ((InternalEObject)dateLiteral).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - STCorePackage.ST_DATE_LITERAL__DATE_LITERAL, null, msgs);
      if (newDateLiteral != null)
        msgs = ((InternalEObject)newDateLiteral).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - STCorePackage.ST_DATE_LITERAL__DATE_LITERAL, null, msgs);
      msgs = basicSetDateLiteral(newDateLiteral, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, STCorePackage.ST_DATE_LITERAL__DATE_LITERAL, newDateLiteral, newDateLiteral));
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
      case STCorePackage.ST_DATE_LITERAL__DATE_LITERAL:
        return basicSetDateLiteral(null, msgs);
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
      case STCorePackage.ST_DATE_LITERAL__DATE_LITERAL:
        return getDateLiteral();
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
      case STCorePackage.ST_DATE_LITERAL__DATE_LITERAL:
        setDateLiteral((DATE_LITERAL)newValue);
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
      case STCorePackage.ST_DATE_LITERAL__DATE_LITERAL:
        setDateLiteral((DATE_LITERAL)null);
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
      case STCorePackage.ST_DATE_LITERAL__DATE_LITERAL:
        return dateLiteral != null;
    }
    return super.eIsSet(featureID);
  }

} //STDateLiteralImpl
