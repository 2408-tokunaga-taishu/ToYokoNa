package com.example.ToYokoNA.controller.form;

import jakarta.validation.GroupSequence;

//@GroupSequenceアノテーションは、バリデーションの順番を設定することができる。左から順番にバリデーションされる
@GroupSequence({ ValidGroup1.class, ValidGroup2.class })
public interface GroupOrder {
}
