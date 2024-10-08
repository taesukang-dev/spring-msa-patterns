/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.example.delivery.infrastructure.model;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class RestaurantApprovalResponseAvroModel extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -1685969047939728693L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"RestaurantApprovalResponseAvroModel\",\"namespace\":\"com.example.delivery.infrastructure.model\",\"fields\":[{\"name\":\"orderId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"sagaId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"userId\",\"type\":\"long\"},{\"name\":\"restaurantId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"deliveryAddress\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"orderStatus\",\"type\":{\"type\":\"enum\",\"name\":\"RestaurantOrderStatus\",\"symbols\":[\"PENDING\",\"PAID\",\"APPROVED\",\"CANCELLED\"]}},{\"name\":\"result\",\"type\":\"boolean\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();
  static {
    MODEL$.addLogicalTypeConversion(new org.apache.avro.Conversions.UUIDConversion());
  }

  private static final BinaryMessageEncoder<RestaurantApprovalResponseAvroModel> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<RestaurantApprovalResponseAvroModel> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<RestaurantApprovalResponseAvroModel> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<RestaurantApprovalResponseAvroModel> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<RestaurantApprovalResponseAvroModel> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this RestaurantApprovalResponseAvroModel to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a RestaurantApprovalResponseAvroModel from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a RestaurantApprovalResponseAvroModel instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static RestaurantApprovalResponseAvroModel fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.util.UUID orderId;
  private java.util.UUID sagaId;
  private long userId;
  private java.util.UUID restaurantId;
  private java.lang.String deliveryAddress;
  private com.example.delivery.infrastructure.model.RestaurantOrderStatus orderStatus;
  private boolean result;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public RestaurantApprovalResponseAvroModel() {}

  /**
   * All-args constructor.
   * @param orderId The new value for orderId
   * @param sagaId The new value for sagaId
   * @param userId The new value for userId
   * @param restaurantId The new value for restaurantId
   * @param deliveryAddress The new value for deliveryAddress
   * @param orderStatus The new value for orderStatus
   * @param result The new value for result
   */
  public RestaurantApprovalResponseAvroModel(java.util.UUID orderId, java.util.UUID sagaId, java.lang.Long userId, java.util.UUID restaurantId, java.lang.String deliveryAddress, com.example.delivery.infrastructure.model.RestaurantOrderStatus orderStatus, java.lang.Boolean result) {
    this.orderId = orderId;
    this.sagaId = sagaId;
    this.userId = userId;
    this.restaurantId = restaurantId;
    this.deliveryAddress = deliveryAddress;
    this.orderStatus = orderStatus;
    this.result = result;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return orderId;
    case 1: return sagaId;
    case 2: return userId;
    case 3: return restaurantId;
    case 4: return deliveryAddress;
    case 5: return orderStatus;
    case 6: return result;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  private static final org.apache.avro.Conversion<?>[] conversions =
      new org.apache.avro.Conversion<?>[] {
      new org.apache.avro.Conversions.UUIDConversion(),
      new org.apache.avro.Conversions.UUIDConversion(),
      null,
      new org.apache.avro.Conversions.UUIDConversion(),
      null,
      null,
      null,
      null
  };

  @Override
  public org.apache.avro.Conversion<?> getConversion(int field) {
    return conversions[field];
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: orderId = (java.util.UUID)value$; break;
    case 1: sagaId = (java.util.UUID)value$; break;
    case 2: userId = (java.lang.Long)value$; break;
    case 3: restaurantId = (java.util.UUID)value$; break;
    case 4: deliveryAddress = value$ != null ? value$.toString() : null; break;
    case 5: orderStatus = (com.example.delivery.infrastructure.model.RestaurantOrderStatus)value$; break;
    case 6: result = (java.lang.Boolean)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'orderId' field.
   * @return The value of the 'orderId' field.
   */
  public java.util.UUID getOrderId() {
    return orderId;
  }


  /**
   * Sets the value of the 'orderId' field.
   * @param value the value to set.
   */
  public void setOrderId(java.util.UUID value) {
    this.orderId = value;
  }

  /**
   * Gets the value of the 'sagaId' field.
   * @return The value of the 'sagaId' field.
   */
  public java.util.UUID getSagaId() {
    return sagaId;
  }


  /**
   * Sets the value of the 'sagaId' field.
   * @param value the value to set.
   */
  public void setSagaId(java.util.UUID value) {
    this.sagaId = value;
  }

  /**
   * Gets the value of the 'userId' field.
   * @return The value of the 'userId' field.
   */
  public long getUserId() {
    return userId;
  }


  /**
   * Sets the value of the 'userId' field.
   * @param value the value to set.
   */
  public void setUserId(long value) {
    this.userId = value;
  }

  /**
   * Gets the value of the 'restaurantId' field.
   * @return The value of the 'restaurantId' field.
   */
  public java.util.UUID getRestaurantId() {
    return restaurantId;
  }


  /**
   * Sets the value of the 'restaurantId' field.
   * @param value the value to set.
   */
  public void setRestaurantId(java.util.UUID value) {
    this.restaurantId = value;
  }

  /**
   * Gets the value of the 'deliveryAddress' field.
   * @return The value of the 'deliveryAddress' field.
   */
  public java.lang.String getDeliveryAddress() {
    return deliveryAddress;
  }


  /**
   * Sets the value of the 'deliveryAddress' field.
   * @param value the value to set.
   */
  public void setDeliveryAddress(java.lang.String value) {
    this.deliveryAddress = value;
  }

  /**
   * Gets the value of the 'orderStatus' field.
   * @return The value of the 'orderStatus' field.
   */
  public com.example.delivery.infrastructure.model.RestaurantOrderStatus getOrderStatus() {
    return orderStatus;
  }


  /**
   * Sets the value of the 'orderStatus' field.
   * @param value the value to set.
   */
  public void setOrderStatus(com.example.delivery.infrastructure.model.RestaurantOrderStatus value) {
    this.orderStatus = value;
  }

  /**
   * Gets the value of the 'result' field.
   * @return The value of the 'result' field.
   */
  public boolean getResult() {
    return result;
  }


  /**
   * Sets the value of the 'result' field.
   * @param value the value to set.
   */
  public void setResult(boolean value) {
    this.result = value;
  }

  /**
   * Creates a new RestaurantApprovalResponseAvroModel RecordBuilder.
   * @return A new RestaurantApprovalResponseAvroModel RecordBuilder
   */
  public static com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel.Builder newBuilder() {
    return new com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel.Builder();
  }

  /**
   * Creates a new RestaurantApprovalResponseAvroModel RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new RestaurantApprovalResponseAvroModel RecordBuilder
   */
  public static com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel.Builder newBuilder(com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel.Builder other) {
    if (other == null) {
      return new com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel.Builder();
    } else {
      return new com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel.Builder(other);
    }
  }

  /**
   * Creates a new RestaurantApprovalResponseAvroModel RecordBuilder by copying an existing RestaurantApprovalResponseAvroModel instance.
   * @param other The existing instance to copy.
   * @return A new RestaurantApprovalResponseAvroModel RecordBuilder
   */
  public static com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel.Builder newBuilder(com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel other) {
    if (other == null) {
      return new com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel.Builder();
    } else {
      return new com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel.Builder(other);
    }
  }

  /**
   * RecordBuilder for RestaurantApprovalResponseAvroModel instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<RestaurantApprovalResponseAvroModel>
    implements org.apache.avro.data.RecordBuilder<RestaurantApprovalResponseAvroModel> {

    private java.util.UUID orderId;
    private java.util.UUID sagaId;
    private long userId;
    private java.util.UUID restaurantId;
    private java.lang.String deliveryAddress;
    private com.example.delivery.infrastructure.model.RestaurantOrderStatus orderStatus;
    private boolean result;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.orderId)) {
        this.orderId = data().deepCopy(fields()[0].schema(), other.orderId);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.sagaId)) {
        this.sagaId = data().deepCopy(fields()[1].schema(), other.sagaId);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.userId)) {
        this.userId = data().deepCopy(fields()[2].schema(), other.userId);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.restaurantId)) {
        this.restaurantId = data().deepCopy(fields()[3].schema(), other.restaurantId);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.deliveryAddress)) {
        this.deliveryAddress = data().deepCopy(fields()[4].schema(), other.deliveryAddress);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.orderStatus)) {
        this.orderStatus = data().deepCopy(fields()[5].schema(), other.orderStatus);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.result)) {
        this.result = data().deepCopy(fields()[6].schema(), other.result);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
    }

    /**
     * Creates a Builder by copying an existing RestaurantApprovalResponseAvroModel instance
     * @param other The existing instance to copy.
     */
    private Builder(com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.orderId)) {
        this.orderId = data().deepCopy(fields()[0].schema(), other.orderId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.sagaId)) {
        this.sagaId = data().deepCopy(fields()[1].schema(), other.sagaId);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.userId)) {
        this.userId = data().deepCopy(fields()[2].schema(), other.userId);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.restaurantId)) {
        this.restaurantId = data().deepCopy(fields()[3].schema(), other.restaurantId);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.deliveryAddress)) {
        this.deliveryAddress = data().deepCopy(fields()[4].schema(), other.deliveryAddress);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.orderStatus)) {
        this.orderStatus = data().deepCopy(fields()[5].schema(), other.orderStatus);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.result)) {
        this.result = data().deepCopy(fields()[6].schema(), other.result);
        fieldSetFlags()[6] = true;
      }
    }

    /**
      * Gets the value of the 'orderId' field.
      * @return The value.
      */
    public java.util.UUID getOrderId() {
      return orderId;
    }


    /**
      * Sets the value of the 'orderId' field.
      * @param value The value of 'orderId'.
      * @return This builder.
      */
    public com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel.Builder setOrderId(java.util.UUID value) {
      validate(fields()[0], value);
      this.orderId = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'orderId' field has been set.
      * @return True if the 'orderId' field has been set, false otherwise.
      */
    public boolean hasOrderId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'orderId' field.
      * @return This builder.
      */
    public com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel.Builder clearOrderId() {
      orderId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'sagaId' field.
      * @return The value.
      */
    public java.util.UUID getSagaId() {
      return sagaId;
    }


    /**
      * Sets the value of the 'sagaId' field.
      * @param value The value of 'sagaId'.
      * @return This builder.
      */
    public com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel.Builder setSagaId(java.util.UUID value) {
      validate(fields()[1], value);
      this.sagaId = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'sagaId' field has been set.
      * @return True if the 'sagaId' field has been set, false otherwise.
      */
    public boolean hasSagaId() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'sagaId' field.
      * @return This builder.
      */
    public com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel.Builder clearSagaId() {
      sagaId = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'userId' field.
      * @return The value.
      */
    public long getUserId() {
      return userId;
    }


    /**
      * Sets the value of the 'userId' field.
      * @param value The value of 'userId'.
      * @return This builder.
      */
    public com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel.Builder setUserId(long value) {
      validate(fields()[2], value);
      this.userId = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'userId' field has been set.
      * @return True if the 'userId' field has been set, false otherwise.
      */
    public boolean hasUserId() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'userId' field.
      * @return This builder.
      */
    public com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel.Builder clearUserId() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'restaurantId' field.
      * @return The value.
      */
    public java.util.UUID getRestaurantId() {
      return restaurantId;
    }


    /**
      * Sets the value of the 'restaurantId' field.
      * @param value The value of 'restaurantId'.
      * @return This builder.
      */
    public com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel.Builder setRestaurantId(java.util.UUID value) {
      validate(fields()[3], value);
      this.restaurantId = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'restaurantId' field has been set.
      * @return True if the 'restaurantId' field has been set, false otherwise.
      */
    public boolean hasRestaurantId() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'restaurantId' field.
      * @return This builder.
      */
    public com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel.Builder clearRestaurantId() {
      restaurantId = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'deliveryAddress' field.
      * @return The value.
      */
    public java.lang.String getDeliveryAddress() {
      return deliveryAddress;
    }


    /**
      * Sets the value of the 'deliveryAddress' field.
      * @param value The value of 'deliveryAddress'.
      * @return This builder.
      */
    public com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel.Builder setDeliveryAddress(java.lang.String value) {
      validate(fields()[4], value);
      this.deliveryAddress = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'deliveryAddress' field has been set.
      * @return True if the 'deliveryAddress' field has been set, false otherwise.
      */
    public boolean hasDeliveryAddress() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'deliveryAddress' field.
      * @return This builder.
      */
    public com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel.Builder clearDeliveryAddress() {
      deliveryAddress = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'orderStatus' field.
      * @return The value.
      */
    public com.example.delivery.infrastructure.model.RestaurantOrderStatus getOrderStatus() {
      return orderStatus;
    }


    /**
      * Sets the value of the 'orderStatus' field.
      * @param value The value of 'orderStatus'.
      * @return This builder.
      */
    public com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel.Builder setOrderStatus(com.example.delivery.infrastructure.model.RestaurantOrderStatus value) {
      validate(fields()[5], value);
      this.orderStatus = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'orderStatus' field has been set.
      * @return True if the 'orderStatus' field has been set, false otherwise.
      */
    public boolean hasOrderStatus() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'orderStatus' field.
      * @return This builder.
      */
    public com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel.Builder clearOrderStatus() {
      orderStatus = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'result' field.
      * @return The value.
      */
    public boolean getResult() {
      return result;
    }


    /**
      * Sets the value of the 'result' field.
      * @param value The value of 'result'.
      * @return This builder.
      */
    public com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel.Builder setResult(boolean value) {
      validate(fields()[6], value);
      this.result = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'result' field has been set.
      * @return True if the 'result' field has been set, false otherwise.
      */
    public boolean hasResult() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'result' field.
      * @return This builder.
      */
    public com.example.delivery.infrastructure.model.RestaurantApprovalResponseAvroModel.Builder clearResult() {
      fieldSetFlags()[6] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public RestaurantApprovalResponseAvroModel build() {
      try {
        RestaurantApprovalResponseAvroModel record = new RestaurantApprovalResponseAvroModel();
        record.orderId = fieldSetFlags()[0] ? this.orderId : (java.util.UUID) defaultValue(fields()[0]);
        record.sagaId = fieldSetFlags()[1] ? this.sagaId : (java.util.UUID) defaultValue(fields()[1]);
        record.userId = fieldSetFlags()[2] ? this.userId : (java.lang.Long) defaultValue(fields()[2]);
        record.restaurantId = fieldSetFlags()[3] ? this.restaurantId : (java.util.UUID) defaultValue(fields()[3]);
        record.deliveryAddress = fieldSetFlags()[4] ? this.deliveryAddress : (java.lang.String) defaultValue(fields()[4]);
        record.orderStatus = fieldSetFlags()[5] ? this.orderStatus : (com.example.delivery.infrastructure.model.RestaurantOrderStatus) defaultValue(fields()[5]);
        record.result = fieldSetFlags()[6] ? this.result : (java.lang.Boolean) defaultValue(fields()[6]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<RestaurantApprovalResponseAvroModel>
    WRITER$ = (org.apache.avro.io.DatumWriter<RestaurantApprovalResponseAvroModel>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<RestaurantApprovalResponseAvroModel>
    READER$ = (org.apache.avro.io.DatumReader<RestaurantApprovalResponseAvroModel>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}










