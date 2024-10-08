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
public class OrderPaymentResponseAvroModel extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 1156850006731104731L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"OrderPaymentResponseAvroModel\",\"namespace\":\"com.example.delivery.infrastructure.model\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"orderId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"sagaId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"createdAt\",\"type\":\"long\",\"logicalType\":\"timestamp-millis\"},{\"name\":\"outboxStatus\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"totalPrice\",\"type\":{\"type\":\"bytes\",\"logicalType\":\"decimal\",\"precision\":10,\"scale\":2}},{\"name\":\"userId\",\"type\":\"long\"},{\"name\":\"result\",\"type\":\"boolean\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();
  static {
    MODEL$.addLogicalTypeConversion(new org.apache.avro.Conversions.UUIDConversion());
    MODEL$.addLogicalTypeConversion(new org.apache.avro.Conversions.DecimalConversion());
  }

  private static final BinaryMessageEncoder<OrderPaymentResponseAvroModel> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<OrderPaymentResponseAvroModel> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<OrderPaymentResponseAvroModel> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<OrderPaymentResponseAvroModel> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<OrderPaymentResponseAvroModel> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this OrderPaymentResponseAvroModel to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a OrderPaymentResponseAvroModel from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a OrderPaymentResponseAvroModel instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static OrderPaymentResponseAvroModel fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.util.UUID id;
  private java.util.UUID orderId;
  private java.util.UUID sagaId;
  private long createdAt;
  private java.lang.String outboxStatus;
  private java.math.BigDecimal totalPrice;
  private long userId;
  private boolean result;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public OrderPaymentResponseAvroModel() {}

  /**
   * All-args constructor.
   * @param id The new value for id
   * @param orderId The new value for orderId
   * @param sagaId The new value for sagaId
   * @param createdAt The new value for createdAt
   * @param outboxStatus The new value for outboxStatus
   * @param totalPrice The new value for totalPrice
   * @param userId The new value for userId
   * @param result The new value for result
   */
  public OrderPaymentResponseAvroModel(java.util.UUID id, java.util.UUID orderId, java.util.UUID sagaId, java.lang.Long createdAt, java.lang.String outboxStatus, java.math.BigDecimal totalPrice, java.lang.Long userId, java.lang.Boolean result) {
    this.id = id;
    this.orderId = orderId;
    this.sagaId = sagaId;
    this.createdAt = createdAt;
    this.outboxStatus = outboxStatus;
    this.totalPrice = totalPrice;
    this.userId = userId;
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
    case 0: return id;
    case 1: return orderId;
    case 2: return sagaId;
    case 3: return createdAt;
    case 4: return outboxStatus;
    case 5: return totalPrice;
    case 6: return userId;
    case 7: return result;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  private static final org.apache.avro.Conversion<?>[] conversions =
      new org.apache.avro.Conversion<?>[] {
      new org.apache.avro.Conversions.UUIDConversion(),
      new org.apache.avro.Conversions.UUIDConversion(),
      new org.apache.avro.Conversions.UUIDConversion(),
      null,
      null,
      new org.apache.avro.Conversions.DecimalConversion(),
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
    case 0: id = (java.util.UUID)value$; break;
    case 1: orderId = (java.util.UUID)value$; break;
    case 2: sagaId = (java.util.UUID)value$; break;
    case 3: createdAt = (java.lang.Long)value$; break;
    case 4: outboxStatus = value$ != null ? value$.toString() : null; break;
    case 5: totalPrice = (java.math.BigDecimal)value$; break;
    case 6: userId = (java.lang.Long)value$; break;
    case 7: result = (java.lang.Boolean)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return The value of the 'id' field.
   */
  public java.util.UUID getId() {
    return id;
  }


  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.util.UUID value) {
    this.id = value;
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
   * Gets the value of the 'createdAt' field.
   * @return The value of the 'createdAt' field.
   */
  public long getCreatedAt() {
    return createdAt;
  }


  /**
   * Sets the value of the 'createdAt' field.
   * @param value the value to set.
   */
  public void setCreatedAt(long value) {
    this.createdAt = value;
  }

  /**
   * Gets the value of the 'outboxStatus' field.
   * @return The value of the 'outboxStatus' field.
   */
  public java.lang.String getOutboxStatus() {
    return outboxStatus;
  }


  /**
   * Sets the value of the 'outboxStatus' field.
   * @param value the value to set.
   */
  public void setOutboxStatus(java.lang.String value) {
    this.outboxStatus = value;
  }

  /**
   * Gets the value of the 'totalPrice' field.
   * @return The value of the 'totalPrice' field.
   */
  public java.math.BigDecimal getTotalPrice() {
    return totalPrice;
  }


  /**
   * Sets the value of the 'totalPrice' field.
   * @param value the value to set.
   */
  public void setTotalPrice(java.math.BigDecimal value) {
    this.totalPrice = value;
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
   * Creates a new OrderPaymentResponseAvroModel RecordBuilder.
   * @return A new OrderPaymentResponseAvroModel RecordBuilder
   */
  public static com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel.Builder newBuilder() {
    return new com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel.Builder();
  }

  /**
   * Creates a new OrderPaymentResponseAvroModel RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new OrderPaymentResponseAvroModel RecordBuilder
   */
  public static com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel.Builder newBuilder(com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel.Builder other) {
    if (other == null) {
      return new com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel.Builder();
    } else {
      return new com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel.Builder(other);
    }
  }

  /**
   * Creates a new OrderPaymentResponseAvroModel RecordBuilder by copying an existing OrderPaymentResponseAvroModel instance.
   * @param other The existing instance to copy.
   * @return A new OrderPaymentResponseAvroModel RecordBuilder
   */
  public static com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel.Builder newBuilder(com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel other) {
    if (other == null) {
      return new com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel.Builder();
    } else {
      return new com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel.Builder(other);
    }
  }

  /**
   * RecordBuilder for OrderPaymentResponseAvroModel instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<OrderPaymentResponseAvroModel>
    implements org.apache.avro.data.RecordBuilder<OrderPaymentResponseAvroModel> {

    private java.util.UUID id;
    private java.util.UUID orderId;
    private java.util.UUID sagaId;
    private long createdAt;
    private java.lang.String outboxStatus;
    private java.math.BigDecimal totalPrice;
    private long userId;
    private boolean result;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.orderId)) {
        this.orderId = data().deepCopy(fields()[1].schema(), other.orderId);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.sagaId)) {
        this.sagaId = data().deepCopy(fields()[2].schema(), other.sagaId);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.createdAt)) {
        this.createdAt = data().deepCopy(fields()[3].schema(), other.createdAt);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.outboxStatus)) {
        this.outboxStatus = data().deepCopy(fields()[4].schema(), other.outboxStatus);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.totalPrice)) {
        this.totalPrice = data().deepCopy(fields()[5].schema(), other.totalPrice);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.userId)) {
        this.userId = data().deepCopy(fields()[6].schema(), other.userId);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
      if (isValidValue(fields()[7], other.result)) {
        this.result = data().deepCopy(fields()[7].schema(), other.result);
        fieldSetFlags()[7] = other.fieldSetFlags()[7];
      }
    }

    /**
     * Creates a Builder by copying an existing OrderPaymentResponseAvroModel instance
     * @param other The existing instance to copy.
     */
    private Builder(com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.orderId)) {
        this.orderId = data().deepCopy(fields()[1].schema(), other.orderId);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.sagaId)) {
        this.sagaId = data().deepCopy(fields()[2].schema(), other.sagaId);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.createdAt)) {
        this.createdAt = data().deepCopy(fields()[3].schema(), other.createdAt);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.outboxStatus)) {
        this.outboxStatus = data().deepCopy(fields()[4].schema(), other.outboxStatus);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.totalPrice)) {
        this.totalPrice = data().deepCopy(fields()[5].schema(), other.totalPrice);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.userId)) {
        this.userId = data().deepCopy(fields()[6].schema(), other.userId);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.result)) {
        this.result = data().deepCopy(fields()[7].schema(), other.result);
        fieldSetFlags()[7] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public java.util.UUID getId() {
      return id;
    }


    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel.Builder setId(java.util.UUID value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel.Builder clearId() {
      id = null;
      fieldSetFlags()[0] = false;
      return this;
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
    public com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel.Builder setOrderId(java.util.UUID value) {
      validate(fields()[1], value);
      this.orderId = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'orderId' field has been set.
      * @return True if the 'orderId' field has been set, false otherwise.
      */
    public boolean hasOrderId() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'orderId' field.
      * @return This builder.
      */
    public com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel.Builder clearOrderId() {
      orderId = null;
      fieldSetFlags()[1] = false;
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
    public com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel.Builder setSagaId(java.util.UUID value) {
      validate(fields()[2], value);
      this.sagaId = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'sagaId' field has been set.
      * @return True if the 'sagaId' field has been set, false otherwise.
      */
    public boolean hasSagaId() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'sagaId' field.
      * @return This builder.
      */
    public com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel.Builder clearSagaId() {
      sagaId = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'createdAt' field.
      * @return The value.
      */
    public long getCreatedAt() {
      return createdAt;
    }


    /**
      * Sets the value of the 'createdAt' field.
      * @param value The value of 'createdAt'.
      * @return This builder.
      */
    public com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel.Builder setCreatedAt(long value) {
      validate(fields()[3], value);
      this.createdAt = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'createdAt' field has been set.
      * @return True if the 'createdAt' field has been set, false otherwise.
      */
    public boolean hasCreatedAt() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'createdAt' field.
      * @return This builder.
      */
    public com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel.Builder clearCreatedAt() {
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'outboxStatus' field.
      * @return The value.
      */
    public java.lang.String getOutboxStatus() {
      return outboxStatus;
    }


    /**
      * Sets the value of the 'outboxStatus' field.
      * @param value The value of 'outboxStatus'.
      * @return This builder.
      */
    public com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel.Builder setOutboxStatus(java.lang.String value) {
      validate(fields()[4], value);
      this.outboxStatus = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'outboxStatus' field has been set.
      * @return True if the 'outboxStatus' field has been set, false otherwise.
      */
    public boolean hasOutboxStatus() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'outboxStatus' field.
      * @return This builder.
      */
    public com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel.Builder clearOutboxStatus() {
      outboxStatus = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'totalPrice' field.
      * @return The value.
      */
    public java.math.BigDecimal getTotalPrice() {
      return totalPrice;
    }


    /**
      * Sets the value of the 'totalPrice' field.
      * @param value The value of 'totalPrice'.
      * @return This builder.
      */
    public com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel.Builder setTotalPrice(java.math.BigDecimal value) {
      validate(fields()[5], value);
      this.totalPrice = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'totalPrice' field has been set.
      * @return True if the 'totalPrice' field has been set, false otherwise.
      */
    public boolean hasTotalPrice() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'totalPrice' field.
      * @return This builder.
      */
    public com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel.Builder clearTotalPrice() {
      totalPrice = null;
      fieldSetFlags()[5] = false;
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
    public com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel.Builder setUserId(long value) {
      validate(fields()[6], value);
      this.userId = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'userId' field has been set.
      * @return True if the 'userId' field has been set, false otherwise.
      */
    public boolean hasUserId() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'userId' field.
      * @return This builder.
      */
    public com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel.Builder clearUserId() {
      fieldSetFlags()[6] = false;
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
    public com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel.Builder setResult(boolean value) {
      validate(fields()[7], value);
      this.result = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'result' field has been set.
      * @return True if the 'result' field has been set, false otherwise.
      */
    public boolean hasResult() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'result' field.
      * @return This builder.
      */
    public com.example.delivery.infrastructure.model.OrderPaymentResponseAvroModel.Builder clearResult() {
      fieldSetFlags()[7] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public OrderPaymentResponseAvroModel build() {
      try {
        OrderPaymentResponseAvroModel record = new OrderPaymentResponseAvroModel();
        record.id = fieldSetFlags()[0] ? this.id : (java.util.UUID) defaultValue(fields()[0]);
        record.orderId = fieldSetFlags()[1] ? this.orderId : (java.util.UUID) defaultValue(fields()[1]);
        record.sagaId = fieldSetFlags()[2] ? this.sagaId : (java.util.UUID) defaultValue(fields()[2]);
        record.createdAt = fieldSetFlags()[3] ? this.createdAt : (java.lang.Long) defaultValue(fields()[3]);
        record.outboxStatus = fieldSetFlags()[4] ? this.outboxStatus : (java.lang.String) defaultValue(fields()[4]);
        record.totalPrice = fieldSetFlags()[5] ? this.totalPrice : (java.math.BigDecimal) defaultValue(fields()[5]);
        record.userId = fieldSetFlags()[6] ? this.userId : (java.lang.Long) defaultValue(fields()[6]);
        record.result = fieldSetFlags()[7] ? this.result : (java.lang.Boolean) defaultValue(fields()[7]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<OrderPaymentResponseAvroModel>
    WRITER$ = (org.apache.avro.io.DatumWriter<OrderPaymentResponseAvroModel>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<OrderPaymentResponseAvroModel>
    READER$ = (org.apache.avro.io.DatumReader<OrderPaymentResponseAvroModel>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}










