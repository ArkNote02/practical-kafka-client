package com.github.jiantailang.messages;

import com.github.jiantailang.kafka.domain.models.Data;

/**
 * 送受信に使用するメッセージはプロトコルの一種として管理する.
 * 単純なgetter, setterのみで構成しドメインとの受け渡しに特別な処理が必要な場合はアダプターにパーサーを設ける.
 */
public class DataForMessage {

  private String value;

  public DataForMessage() {}

  public DataForMessage(final String value) {
    this.value = value;
  }

  public DataForMessage(final Data data) {
    this.value = data.value();
  }

  public String value() {
    return value;
  }

  public Data toData() {
    return new Data(value);
  }

  public void setValue(final String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
