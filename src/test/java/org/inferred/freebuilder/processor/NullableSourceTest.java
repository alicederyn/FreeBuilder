/*
 * Copyright 2015 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.inferred.freebuilder.processor;

import static com.google.common.truth.Truth.assertThat;

import static org.inferred.freebuilder.processor.util.ClassTypeImpl.newTopLevelClass;
import static org.inferred.freebuilder.processor.util.feature.SourceLevel.JAVA_7;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSet;
import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;

import org.inferred.freebuilder.processor.Metadata.Property;
import org.inferred.freebuilder.processor.util.ClassTypeImpl;
import org.inferred.freebuilder.processor.util.ClassTypeImpl.ClassElementImpl;
import org.inferred.freebuilder.processor.util.QualifiedName;
import org.inferred.freebuilder.processor.util.SourceBuilder;
import org.inferred.freebuilder.processor.util.SourceStringBuilder;
import org.inferred.freebuilder.processor.util.feature.Feature;
import org.inferred.freebuilder.processor.util.feature.GuavaLibrary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class NullableSourceTest {

  @Test
  public void testJ6() {
    assertThat(generateSource(metadata(), GuavaLibrary.AVAILABLE)).isEqualTo(Joiner.on('\n').join(
        "/**",
        " * Auto-generated superclass of {@link Person.Builder},",
        " * derived from the API of {@link Person}.",
        " */",
        "@Generated(\"org.inferred.freebuilder.processor.CodeGenerator\")",
        "abstract class Person_Builder {",
        "",
        "  private static final Joiner COMMA_JOINER = Joiner.on(\", \").skipNulls();",
        "",
        "  @Nullable private String name = null;",
        "  @Nullable private Integer age = null;",
        "",
        "  /**",
        "   * Sets the value to be returned by {@link Person#getName()}.",
        "   *",
        "   * @return this {@code Builder} object",
        "   */",
        "  public Person.Builder setName(@Nullable String name) {",
        "    this.name = name;",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Returns the value that will be returned by {@link Person#getName()}.",
        "   */",
        "  @Nullable",
        "  public String getName() {",
        "    return name;",
        "  }",
        "",
        "  /**",
        "   * Sets the value to be returned by {@link Person#getAge()}.",
        "   *",
        "   * @return this {@code Builder} object",
        "   */",
        "  public Person.Builder setAge(@Nullable Integer age) {",
        "    this.age = age;",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Returns the value that will be returned by {@link Person#getAge()}.",
        "   */",
        "  @Nullable",
        "  public Integer getAge() {",
        "    return age;",
        "  }",
        "",
        "  /**",
        "   * Sets all property values using the given {@code Person} as a template.",
        "   */",
        "  public Person.Builder mergeFrom(Person value) {",
        "    setName(value.getName());",
        "    setAge(value.getAge());",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Copies values from the given {@code Builder}.",
        "   */",
        "  public Person.Builder mergeFrom(Person.Builder template) {",
        "    setName(template.getName());",
        "    setAge(template.getAge());",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Resets the state of this builder.",
        "   */",
        "  public Person.Builder clear() {",
        "    Person_Builder _template = new Person.Builder();",
        "    name = _template.name;",
        "    age = _template.age;",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Returns a newly-created {@link Person} based on the contents of the {@code Builder}.",
        "   */",
        "  public Person build() {",
        "    return new Person_Builder.Value(this);",
        "  }",
        "",
        "  /**",
        "   * Returns a newly-created partial {@link Person}",
        "   * based on the contents of the {@code Builder}.",
        "   * State checking will not be performed.",
        "   *",
        "   * <p>Partials should only ever be used in tests.",
        "   */",
        "  @VisibleForTesting()",
        "  public Person buildPartial() {",
        "    return new Person_Builder.Partial(this);",
        "  }",
        "",
        "  private static final class Value extends Person {",
        "    @Nullable private final String name;",
        "    @Nullable private final Integer age;",
        "",
        "    private Value(Person_Builder builder) {",
        "      this.name = builder.name;",
        "      this.age = builder.age;",
        "    }",
        "",
        "    @Override",
        "    @Nullable",
        "    public String getName() {",
        "      return name;",
        "    }",
        "",
        "    @Override",
        "    @Nullable",
        "    public Integer getAge() {",
        "      return age;",
        "    }",
        "",
        "    @Override",
        "    public boolean equals(Object obj) {",
        "      if (!(obj instanceof Person_Builder.Value)) {",
        "        return false;",
        "      }",
        "      Person_Builder.Value other = (Person_Builder.Value) obj;",
        "      if (name != other.name && (name == null || !name.equals(other.name))) {",
        "        return false;",
        "      }",
        "      if (age != other.age && (age == null || !age.equals(other.age))) {",
        "        return false;",
        "      }",
        "      return true;",
        "    }",
        "",
        "    @Override",
        "    public int hashCode() {",
        "      return Arrays.hashCode(new Object[] {name, age});",
        "    }",
        "",
        "    @Override",
        "    public String toString() {",
        "      return \"Person{\"",
        "          + COMMA_JOINER.join(",
        "              (name != null ? \"name=\" + name : null), " +
            "(age != null ? \"age=\" + age : null))",
        "          + \"}\";",
        "    }",
        "  }",
        "",
        "  private static final class Partial extends Person {",
        "    @Nullable private final String name;",
        "    @Nullable private final Integer age;",
        "",
        "    Partial(Person_Builder builder) {",
        "      this.name = builder.name;",
        "      this.age = builder.age;",
        "    }",
        "",
        "    @Override",
        "    @Nullable",
        "    public String getName() {",
        "      return name;",
        "    }",
        "",
        "    @Override",
        "    @Nullable",
        "    public Integer getAge() {",
        "      return age;",
        "    }",
        "",
        "    @Override",
        "    public boolean equals(Object obj) {",
        "      if (!(obj instanceof Person_Builder.Partial)) {",
        "        return false;",
        "      }",
        "      Person_Builder.Partial other = (Person_Builder.Partial) obj;",
        "      if (name != other.name && (name == null || !name.equals(other.name))) {",
        "        return false;",
        "      }",
        "      if (age != other.age && (age == null || !age.equals(other.age))) {",
        "        return false;",
        "      }",
        "      return true;",
        "    }",
        "",
        "    @Override",
        "    public int hashCode() {",
        "      return Arrays.hashCode(new Object[] {name, age});",
        "    }",
        "",
        "    @Override",
        "    public String toString() {",
        "      return \"partial Person{\"",
        "          + COMMA_JOINER.join(",
        "              (name != null ? \"name=\" + name : null), " +
            "(age != null ? \"age=\" + age : null))",
        "          + \"}\";",
        "    }",
        "  }",
        "}\n"));
  }

  @Test
  public void testJ7() {
    String source = generateSource(metadata(), JAVA_7, GuavaLibrary.AVAILABLE);
    assertThat(source).isEqualTo(Joiner.on('\n').join(
        "/**",
        " * Auto-generated superclass of {@link Person.Builder},",
        " * derived from the API of {@link Person}.",
        " */",
        "@Generated(\"org.inferred.freebuilder.processor.CodeGenerator\")",
        "abstract class Person_Builder {",
        "",
        "  private static final Joiner COMMA_JOINER = Joiner.on(\", \").skipNulls();",
        "",
        "  @Nullable private String name = null;",
        "  @Nullable private Integer age = null;",
        "",
        "  /**",
        "   * Sets the value to be returned by {@link Person#getName()}.",
        "   *",
        "   * @return this {@code Builder} object",
        "   */",
        "  public Person.Builder setName(@Nullable String name) {",
        "    this.name = name;",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Returns the value that will be returned by {@link Person#getName()}.",
        "   */",
        "  @Nullable",
        "  public String getName() {",
        "    return name;",
        "  }",
        "",
        "  /**",
        "   * Sets the value to be returned by {@link Person#getAge()}.",
        "   *",
        "   * @return this {@code Builder} object",
        "   */",
        "  public Person.Builder setAge(@Nullable Integer age) {",
        "    this.age = age;",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Returns the value that will be returned by {@link Person#getAge()}.",
        "   */",
        "  @Nullable",
        "  public Integer getAge() {",
        "    return age;",
        "  }",
        "",
        "  /**",
        "   * Sets all property values using the given {@code Person} as a template.",
        "   */",
        "  public Person.Builder mergeFrom(Person value) {",
        "    setName(value.getName());",
        "    setAge(value.getAge());",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Copies values from the given {@code Builder}.",
        "   */",
        "  public Person.Builder mergeFrom(Person.Builder template) {",
        "    setName(template.getName());",
        "    setAge(template.getAge());",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Resets the state of this builder.",
        "   */",
        "  public Person.Builder clear() {",
        "    Person_Builder _template = new Person.Builder();",
        "    name = _template.name;",
        "    age = _template.age;",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Returns a newly-created {@link Person} based on the contents of the {@code Builder}.",
        "   */",
        "  public Person build() {",
        "    return new Person_Builder.Value(this);",
        "  }",
        "",
        "  /**",
        "   * Returns a newly-created partial {@link Person}",
        "   * based on the contents of the {@code Builder}.",
        "   * State checking will not be performed.",
        "   *",
        "   * <p>Partials should only ever be used in tests.",
        "   */",
        "  @VisibleForTesting()",
        "  public Person buildPartial() {",
        "    return new Person_Builder.Partial(this);",
        "  }",
        "",
        "  private static final class Value extends Person {",
        "    @Nullable private final String name;",
        "    @Nullable private final Integer age;",
        "",
        "    private Value(Person_Builder builder) {",
        "      this.name = builder.name;",
        "      this.age = builder.age;",
        "    }",
        "",
        "    @Override",
        "    @Nullable",
        "    public String getName() {",
        "      return name;",
        "    }",
        "",
        "    @Override",
        "    @Nullable",
        "    public Integer getAge() {",
        "      return age;",
        "    }",
        "",
        "    @Override",
        "    public boolean equals(Object obj) {",
        "      if (!(obj instanceof Person_Builder.Value)) {",
        "        return false;",
        "      }",
        "      Person_Builder.Value other = (Person_Builder.Value) obj;",
        "      return Objects.equals(name, other.name) && Objects.equals(age, other.age);",
        "    }",
        "",
        "    @Override",
        "    public int hashCode() {",
        "      return Objects.hash(name, age);",
        "    }",
        "",
        "    @Override",
        "    public String toString() {",
        "      return \"Person{\"",
        "          + COMMA_JOINER.join(",
        "              (name != null ? \"name=\" + name : null), "
            + "(age != null ? \"age=\" + age : null))",
        "          + \"}\";",
        "    }",
        "  }",
        "",
        "  private static final class Partial extends Person {",
        "    @Nullable private final String name;",
        "    @Nullable private final Integer age;",
        "",
        "    Partial(Person_Builder builder) {",
        "      this.name = builder.name;",
        "      this.age = builder.age;",
        "    }",
        "",
        "    @Override",
        "    @Nullable",
        "    public String getName() {",
        "      return name;",
        "    }",
        "",
        "    @Override",
        "    @Nullable",
        "    public Integer getAge() {",
        "      return age;",
        "    }",
        "",
        "    @Override",
        "    public boolean equals(Object obj) {",
        "      if (!(obj instanceof Person_Builder.Partial)) {",
        "        return false;",
        "      }",
        "      Person_Builder.Partial other = (Person_Builder.Partial) obj;",
        "      return Objects.equals(name, other.name) && Objects.equals(age, other.age);",
        "    }",
        "",
        "    @Override",
        "    public int hashCode() {",
        "      return Objects.hash(name, age);",
        "    }",
        "",
        "    @Override",
        "    public String toString() {",
        "      return \"partial Person{\"",
        "          + COMMA_JOINER.join(",
        "              (name != null ? \"name=\" + name : null), "
            + "(age != null ? \"age=\" + age : null))",
        "          + \"}\";",
        "    }",
        "  }",
        "}\n"));
  }

  private static String generateSource(Metadata metadata, Feature<?>... features) {
    SourceBuilder sourceBuilder = SourceStringBuilder.simple(features);
    new CodeGenerator().writeBuilderSource(sourceBuilder, metadata);
    try {
      return new Formatter().formatSource(sourceBuilder.toString());
    } catch (FormatterException e) {
      throw new RuntimeException(e);
    }
  }

  private static Metadata metadata() {
    ClassTypeImpl integer = newTopLevelClass("java.lang.Integer");
    ClassTypeImpl string = newTopLevelClass("java.lang.String");
    ClassElementImpl nullable = newTopLevelClass("javax.annotation.Nullable").asElement();
    QualifiedName person = QualifiedName.of("com.example", "Person");
    QualifiedName generatedBuilder = QualifiedName.of("com.example", "Person_Builder");
    Property.Builder name = new Property.Builder()
        .setAllCapsName("NAME")
        .setBoxedType(string)
        .setCapitalizedName("Name")
        .setFullyCheckedCast(true)
        .setGetterName("getName")
        .setName("name")
        .setType(string);
    Property.Builder age = new Property.Builder()
        .setAllCapsName("AGE")
        .setBoxedType(integer)
        .setCapitalizedName("Age")
        .setFullyCheckedCast(true)
        .setGetterName("getAge")
        .setName("age")
        .setType(integer);
    Metadata metadata = new Metadata.Builder()
        .setBuilder(person.nestedType("Builder").withParameters())
        .setBuilderFactory(BuilderFactory.NO_ARGS_CONSTRUCTOR)
        .setBuilderSerializable(false)
        .setGeneratedBuilder(generatedBuilder.withParameters())
        .setInterfaceType(false)
        .setPartialType(generatedBuilder.nestedType("Partial").withParameters())
        .addProperties(name
            .setCodeGenerator(new NullablePropertyFactory.CodeGenerator(
                name.build(), ImmutableSet.of(nullable)))
            .build())
        .addProperties(age
            .setCodeGenerator(new NullablePropertyFactory.CodeGenerator(
                age.build(), ImmutableSet.of(nullable)))
            .build())
        .setPropertyEnum(generatedBuilder.nestedType("Property").withParameters())
        .setType(person.withParameters())
        .setValueType(generatedBuilder.nestedType("Value").withParameters())
        .build();
    return metadata;
  }

}
