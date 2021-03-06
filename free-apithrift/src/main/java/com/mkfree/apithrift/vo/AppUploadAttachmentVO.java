/**
 * Autogenerated by Thrift Compiler (0.9.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.mkfree.apithrift.vo;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 应用上传附件
 */
public class AppUploadAttachmentVO implements org.apache.thrift.TBase<AppUploadAttachmentVO, AppUploadAttachmentVO._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("AppUploadAttachmentVO");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField URL_FIELD_DESC = new org.apache.thrift.protocol.TField("url", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField ORIGIN_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("originName", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField APP_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("appName", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField DOWNLOAD_COUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("downloadCount", org.apache.thrift.protocol.TType.I64, (short)5);
  private static final org.apache.thrift.protocol.TField USER_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("userId", org.apache.thrift.protocol.TType.STRING, (short)6);
  private static final org.apache.thrift.protocol.TField USER_IP_FIELD_DESC = new org.apache.thrift.protocol.TField("userIp", org.apache.thrift.protocol.TType.STRING, (short)7);
  private static final org.apache.thrift.protocol.TField TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("type", org.apache.thrift.protocol.TType.STRING, (short)8);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new AppUploadAttachmentVOStandardSchemeFactory());
    schemes.put(TupleScheme.class, new AppUploadAttachmentVOTupleSchemeFactory());
  }

  public String id; // required
  public String url; // required
  public String originName; // required
  public String appName; // required
  public long downloadCount; // required
  public String userId; // required
  public String userIp; // required
  public String type; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    URL((short)2, "url"),
    ORIGIN_NAME((short)3, "originName"),
    APP_NAME((short)4, "appName"),
    DOWNLOAD_COUNT((short)5, "downloadCount"),
    USER_ID((short)6, "userId"),
    USER_IP((short)7, "userIp"),
    TYPE((short)8, "type");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // ID
          return ID;
        case 2: // URL
          return URL;
        case 3: // ORIGIN_NAME
          return ORIGIN_NAME;
        case 4: // APP_NAME
          return APP_NAME;
        case 5: // DOWNLOAD_COUNT
          return DOWNLOAD_COUNT;
        case 6: // USER_ID
          return USER_ID;
        case 7: // USER_IP
          return USER_IP;
        case 8: // TYPE
          return TYPE;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __DOWNLOADCOUNT_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.URL, new org.apache.thrift.meta_data.FieldMetaData("url", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ORIGIN_NAME, new org.apache.thrift.meta_data.FieldMetaData("originName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.APP_NAME, new org.apache.thrift.meta_data.FieldMetaData("appName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DOWNLOAD_COUNT, new org.apache.thrift.meta_data.FieldMetaData("downloadCount", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.USER_ID, new org.apache.thrift.meta_data.FieldMetaData("userId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.USER_IP, new org.apache.thrift.meta_data.FieldMetaData("userIp", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TYPE, new org.apache.thrift.meta_data.FieldMetaData("type", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(AppUploadAttachmentVO.class, metaDataMap);
  }

  public AppUploadAttachmentVO() {
  }

  public AppUploadAttachmentVO(
    String id,
    String url,
    String originName,
    String appName,
    long downloadCount,
    String userId,
    String userIp,
    String type)
  {
    this();
    this.id = id;
    this.url = url;
    this.originName = originName;
    this.appName = appName;
    this.downloadCount = downloadCount;
    setDownloadCountIsSet(true);
    this.userId = userId;
    this.userIp = userIp;
    this.type = type;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public AppUploadAttachmentVO(AppUploadAttachmentVO other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetId()) {
      this.id = other.id;
    }
    if (other.isSetUrl()) {
      this.url = other.url;
    }
    if (other.isSetOriginName()) {
      this.originName = other.originName;
    }
    if (other.isSetAppName()) {
      this.appName = other.appName;
    }
    this.downloadCount = other.downloadCount;
    if (other.isSetUserId()) {
      this.userId = other.userId;
    }
    if (other.isSetUserIp()) {
      this.userIp = other.userIp;
    }
    if (other.isSetType()) {
      this.type = other.type;
    }
  }

  public AppUploadAttachmentVO deepCopy() {
    return new AppUploadAttachmentVO(this);
  }

  @Override
  public void clear() {
    this.id = null;
    this.url = null;
    this.originName = null;
    this.appName = null;
    setDownloadCountIsSet(false);
    this.downloadCount = 0;
    this.userId = null;
    this.userIp = null;
    this.type = null;
  }

  public String getId() {
    return this.id;
  }

  public AppUploadAttachmentVO setId(String id) {
    this.id = id;
    return this;
  }

  public void unsetId() {
    this.id = null;
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return this.id != null;
  }

  public void setIdIsSet(boolean value) {
    if (!value) {
      this.id = null;
    }
  }

  public String getUrl() {
    return this.url;
  }

  public AppUploadAttachmentVO setUrl(String url) {
    this.url = url;
    return this;
  }

  public void unsetUrl() {
    this.url = null;
  }

  /** Returns true if field url is set (has been assigned a value) and false otherwise */
  public boolean isSetUrl() {
    return this.url != null;
  }

  public void setUrlIsSet(boolean value) {
    if (!value) {
      this.url = null;
    }
  }

  public String getOriginName() {
    return this.originName;
  }

  public AppUploadAttachmentVO setOriginName(String originName) {
    this.originName = originName;
    return this;
  }

  public void unsetOriginName() {
    this.originName = null;
  }

  /** Returns true if field originName is set (has been assigned a value) and false otherwise */
  public boolean isSetOriginName() {
    return this.originName != null;
  }

  public void setOriginNameIsSet(boolean value) {
    if (!value) {
      this.originName = null;
    }
  }

  public String getAppName() {
    return this.appName;
  }

  public AppUploadAttachmentVO setAppName(String appName) {
    this.appName = appName;
    return this;
  }

  public void unsetAppName() {
    this.appName = null;
  }

  /** Returns true if field appName is set (has been assigned a value) and false otherwise */
  public boolean isSetAppName() {
    return this.appName != null;
  }

  public void setAppNameIsSet(boolean value) {
    if (!value) {
      this.appName = null;
    }
  }

  public long getDownloadCount() {
    return this.downloadCount;
  }

  public AppUploadAttachmentVO setDownloadCount(long downloadCount) {
    this.downloadCount = downloadCount;
    setDownloadCountIsSet(true);
    return this;
  }

  public void unsetDownloadCount() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __DOWNLOADCOUNT_ISSET_ID);
  }

  /** Returns true if field downloadCount is set (has been assigned a value) and false otherwise */
  public boolean isSetDownloadCount() {
    return EncodingUtils.testBit(__isset_bitfield, __DOWNLOADCOUNT_ISSET_ID);
  }

  public void setDownloadCountIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __DOWNLOADCOUNT_ISSET_ID, value);
  }

  public String getUserId() {
    return this.userId;
  }

  public AppUploadAttachmentVO setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public void unsetUserId() {
    this.userId = null;
  }

  /** Returns true if field userId is set (has been assigned a value) and false otherwise */
  public boolean isSetUserId() {
    return this.userId != null;
  }

  public void setUserIdIsSet(boolean value) {
    if (!value) {
      this.userId = null;
    }
  }

  public String getUserIp() {
    return this.userIp;
  }

  public AppUploadAttachmentVO setUserIp(String userIp) {
    this.userIp = userIp;
    return this;
  }

  public void unsetUserIp() {
    this.userIp = null;
  }

  /** Returns true if field userIp is set (has been assigned a value) and false otherwise */
  public boolean isSetUserIp() {
    return this.userIp != null;
  }

  public void setUserIpIsSet(boolean value) {
    if (!value) {
      this.userIp = null;
    }
  }

  public String getType() {
    return this.type;
  }

  public AppUploadAttachmentVO setType(String type) {
    this.type = type;
    return this;
  }

  public void unsetType() {
    this.type = null;
  }

  /** Returns true if field type is set (has been assigned a value) and false otherwise */
  public boolean isSetType() {
    return this.type != null;
  }

  public void setTypeIsSet(boolean value) {
    if (!value) {
      this.type = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((String)value);
      }
      break;

    case URL:
      if (value == null) {
        unsetUrl();
      } else {
        setUrl((String)value);
      }
      break;

    case ORIGIN_NAME:
      if (value == null) {
        unsetOriginName();
      } else {
        setOriginName((String)value);
      }
      break;

    case APP_NAME:
      if (value == null) {
        unsetAppName();
      } else {
        setAppName((String)value);
      }
      break;

    case DOWNLOAD_COUNT:
      if (value == null) {
        unsetDownloadCount();
      } else {
        setDownloadCount((Long)value);
      }
      break;

    case USER_ID:
      if (value == null) {
        unsetUserId();
      } else {
        setUserId((String)value);
      }
      break;

    case USER_IP:
      if (value == null) {
        unsetUserIp();
      } else {
        setUserIp((String)value);
      }
      break;

    case TYPE:
      if (value == null) {
        unsetType();
      } else {
        setType((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return getId();

    case URL:
      return getUrl();

    case ORIGIN_NAME:
      return getOriginName();

    case APP_NAME:
      return getAppName();

    case DOWNLOAD_COUNT:
      return Long.valueOf(getDownloadCount());

    case USER_ID:
      return getUserId();

    case USER_IP:
      return getUserIp();

    case TYPE:
      return getType();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case URL:
      return isSetUrl();
    case ORIGIN_NAME:
      return isSetOriginName();
    case APP_NAME:
      return isSetAppName();
    case DOWNLOAD_COUNT:
      return isSetDownloadCount();
    case USER_ID:
      return isSetUserId();
    case USER_IP:
      return isSetUserIp();
    case TYPE:
      return isSetType();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof AppUploadAttachmentVO)
      return this.equals((AppUploadAttachmentVO)that);
    return false;
  }

  public boolean equals(AppUploadAttachmentVO that) {
    if (that == null)
      return false;

    boolean this_present_id = true && this.isSetId();
    boolean that_present_id = true && that.isSetId();
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (!this.id.equals(that.id))
        return false;
    }

    boolean this_present_url = true && this.isSetUrl();
    boolean that_present_url = true && that.isSetUrl();
    if (this_present_url || that_present_url) {
      if (!(this_present_url && that_present_url))
        return false;
      if (!this.url.equals(that.url))
        return false;
    }

    boolean this_present_originName = true && this.isSetOriginName();
    boolean that_present_originName = true && that.isSetOriginName();
    if (this_present_originName || that_present_originName) {
      if (!(this_present_originName && that_present_originName))
        return false;
      if (!this.originName.equals(that.originName))
        return false;
    }

    boolean this_present_appName = true && this.isSetAppName();
    boolean that_present_appName = true && that.isSetAppName();
    if (this_present_appName || that_present_appName) {
      if (!(this_present_appName && that_present_appName))
        return false;
      if (!this.appName.equals(that.appName))
        return false;
    }

    boolean this_present_downloadCount = true;
    boolean that_present_downloadCount = true;
    if (this_present_downloadCount || that_present_downloadCount) {
      if (!(this_present_downloadCount && that_present_downloadCount))
        return false;
      if (this.downloadCount != that.downloadCount)
        return false;
    }

    boolean this_present_userId = true && this.isSetUserId();
    boolean that_present_userId = true && that.isSetUserId();
    if (this_present_userId || that_present_userId) {
      if (!(this_present_userId && that_present_userId))
        return false;
      if (!this.userId.equals(that.userId))
        return false;
    }

    boolean this_present_userIp = true && this.isSetUserIp();
    boolean that_present_userIp = true && that.isSetUserIp();
    if (this_present_userIp || that_present_userIp) {
      if (!(this_present_userIp && that_present_userIp))
        return false;
      if (!this.userIp.equals(that.userIp))
        return false;
    }

    boolean this_present_type = true && this.isSetType();
    boolean that_present_type = true && that.isSetType();
    if (this_present_type || that_present_type) {
      if (!(this_present_type && that_present_type))
        return false;
      if (!this.type.equals(that.type))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(AppUploadAttachmentVO other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    AppUploadAttachmentVO typedOther = (AppUploadAttachmentVO)other;

    lastComparison = Boolean.valueOf(isSetId()).compareTo(typedOther.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, typedOther.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUrl()).compareTo(typedOther.isSetUrl());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUrl()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.url, typedOther.url);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetOriginName()).compareTo(typedOther.isSetOriginName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOriginName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.originName, typedOther.originName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAppName()).compareTo(typedOther.isSetAppName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAppName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.appName, typedOther.appName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetDownloadCount()).compareTo(typedOther.isSetDownloadCount());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDownloadCount()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.downloadCount, typedOther.downloadCount);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUserId()).compareTo(typedOther.isSetUserId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUserId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.userId, typedOther.userId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUserIp()).compareTo(typedOther.isSetUserIp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUserIp()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.userIp, typedOther.userIp);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetType()).compareTo(typedOther.isSetType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.type, typedOther.type);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("AppUploadAttachmentVO(");
    boolean first = true;

    sb.append("id:");
    if (this.id == null) {
      sb.append("null");
    } else {
      sb.append(this.id);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("url:");
    if (this.url == null) {
      sb.append("null");
    } else {
      sb.append(this.url);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("originName:");
    if (this.originName == null) {
      sb.append("null");
    } else {
      sb.append(this.originName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("appName:");
    if (this.appName == null) {
      sb.append("null");
    } else {
      sb.append(this.appName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("downloadCount:");
    sb.append(this.downloadCount);
    first = false;
    if (!first) sb.append(", ");
    sb.append("userId:");
    if (this.userId == null) {
      sb.append("null");
    } else {
      sb.append(this.userId);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("userIp:");
    if (this.userIp == null) {
      sb.append("null");
    } else {
      sb.append(this.userIp);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("type:");
    if (this.type == null) {
      sb.append("null");
    } else {
      sb.append(this.type);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class AppUploadAttachmentVOStandardSchemeFactory implements SchemeFactory {
    public AppUploadAttachmentVOStandardScheme getScheme() {
      return new AppUploadAttachmentVOStandardScheme();
    }
  }

  private static class AppUploadAttachmentVOStandardScheme extends StandardScheme<AppUploadAttachmentVO> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, AppUploadAttachmentVO struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.id = iprot.readString();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // URL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.url = iprot.readString();
              struct.setUrlIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // ORIGIN_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.originName = iprot.readString();
              struct.setOriginNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // APP_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.appName = iprot.readString();
              struct.setAppNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // DOWNLOAD_COUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.downloadCount = iprot.readI64();
              struct.setDownloadCountIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // USER_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.userId = iprot.readString();
              struct.setUserIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // USER_IP
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.userIp = iprot.readString();
              struct.setUserIpIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 8: // TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.type = iprot.readString();
              struct.setTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, AppUploadAttachmentVO struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.id != null) {
        oprot.writeFieldBegin(ID_FIELD_DESC);
        oprot.writeString(struct.id);
        oprot.writeFieldEnd();
      }
      if (struct.url != null) {
        oprot.writeFieldBegin(URL_FIELD_DESC);
        oprot.writeString(struct.url);
        oprot.writeFieldEnd();
      }
      if (struct.originName != null) {
        oprot.writeFieldBegin(ORIGIN_NAME_FIELD_DESC);
        oprot.writeString(struct.originName);
        oprot.writeFieldEnd();
      }
      if (struct.appName != null) {
        oprot.writeFieldBegin(APP_NAME_FIELD_DESC);
        oprot.writeString(struct.appName);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(DOWNLOAD_COUNT_FIELD_DESC);
      oprot.writeI64(struct.downloadCount);
      oprot.writeFieldEnd();
      if (struct.userId != null) {
        oprot.writeFieldBegin(USER_ID_FIELD_DESC);
        oprot.writeString(struct.userId);
        oprot.writeFieldEnd();
      }
      if (struct.userIp != null) {
        oprot.writeFieldBegin(USER_IP_FIELD_DESC);
        oprot.writeString(struct.userIp);
        oprot.writeFieldEnd();
      }
      if (struct.type != null) {
        oprot.writeFieldBegin(TYPE_FIELD_DESC);
        oprot.writeString(struct.type);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class AppUploadAttachmentVOTupleSchemeFactory implements SchemeFactory {
    public AppUploadAttachmentVOTupleScheme getScheme() {
      return new AppUploadAttachmentVOTupleScheme();
    }
  }

  private static class AppUploadAttachmentVOTupleScheme extends TupleScheme<AppUploadAttachmentVO> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, AppUploadAttachmentVO struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetId()) {
        optionals.set(0);
      }
      if (struct.isSetUrl()) {
        optionals.set(1);
      }
      if (struct.isSetOriginName()) {
        optionals.set(2);
      }
      if (struct.isSetAppName()) {
        optionals.set(3);
      }
      if (struct.isSetDownloadCount()) {
        optionals.set(4);
      }
      if (struct.isSetUserId()) {
        optionals.set(5);
      }
      if (struct.isSetUserIp()) {
        optionals.set(6);
      }
      if (struct.isSetType()) {
        optionals.set(7);
      }
      oprot.writeBitSet(optionals, 8);
      if (struct.isSetId()) {
        oprot.writeString(struct.id);
      }
      if (struct.isSetUrl()) {
        oprot.writeString(struct.url);
      }
      if (struct.isSetOriginName()) {
        oprot.writeString(struct.originName);
      }
      if (struct.isSetAppName()) {
        oprot.writeString(struct.appName);
      }
      if (struct.isSetDownloadCount()) {
        oprot.writeI64(struct.downloadCount);
      }
      if (struct.isSetUserId()) {
        oprot.writeString(struct.userId);
      }
      if (struct.isSetUserIp()) {
        oprot.writeString(struct.userIp);
      }
      if (struct.isSetType()) {
        oprot.writeString(struct.type);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, AppUploadAttachmentVO struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(8);
      if (incoming.get(0)) {
        struct.id = iprot.readString();
        struct.setIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.url = iprot.readString();
        struct.setUrlIsSet(true);
      }
      if (incoming.get(2)) {
        struct.originName = iprot.readString();
        struct.setOriginNameIsSet(true);
      }
      if (incoming.get(3)) {
        struct.appName = iprot.readString();
        struct.setAppNameIsSet(true);
      }
      if (incoming.get(4)) {
        struct.downloadCount = iprot.readI64();
        struct.setDownloadCountIsSet(true);
      }
      if (incoming.get(5)) {
        struct.userId = iprot.readString();
        struct.setUserIdIsSet(true);
      }
      if (incoming.get(6)) {
        struct.userIp = iprot.readString();
        struct.setUserIpIsSet(true);
      }
      if (incoming.get(7)) {
        struct.type = iprot.readString();
        struct.setTypeIsSet(true);
      }
    }
  }

}

