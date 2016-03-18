/*
 * Copyright 2014 Google Inc. All rights reserved.
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

import static org.inferred.freebuilder.processor.BuilderMethods.getter;
import static org.inferred.freebuilder.processor.BuilderMethods.setter;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;

import org.inferred.freebuilder.processor.Metadata.Property;
import org.inferred.freebuilder.processor.PropertyCodeGenerator.Config;
import org.inferred.freebuilder.processor.util.SourceBuilder;

import java.util.Set;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.TypeElement;

/** Default {@link PropertyCodeGenerator.Factory}, providing reference semantics for any type. */
public class NullablePropertyFactory implements PropertyCodeGenerator.Factory {

  @Override
  public Optional<CodeGenerator> create(Config config) {
    Set<TypeElement> nullableAnnotations = nullablesIn(config.getAnnotations());
    if (nullableAnnotations.isEmpty()) {
      return Optional.absent();
    }
    return Optional.of(new CodeGenerator(config.getProperty(), nullableAnnotations));
  }

  private static Set<TypeElement> nullablesIn(Iterable<? extends AnnotationMirror> annotations) {
    ImmutableSet.Builder<TypeElement> nullableAnnotations = ImmutableSet.builder();
    for (AnnotationMirror mirror : annotations) {
      if (mirror.getElementValues().isEmpty()) {
        TypeElement type = (TypeElement) mirror.getAnnotationType().asElement();
        if (type.getSimpleName().contentEquals("Nullable")) {
          nullableAnnotations.add(type);
        }
      }
    }
    return nullableAnnotations.build();
  }

  @VisibleForTesting static class CodeGenerator extends PropertyCodeGenerator {

    private final Set<TypeElement> nullables;

    CodeGenerator(Property property, Iterable<TypeElement> nullableAnnotations) {
      super(property);
      this.nullables = ImmutableSet.copyOf(nullableAnnotations);
    }

    @Override
    public Type getType() {
      return Type.OPTIONAL;
    }

    @Override
    public void addBuilderFieldDeclaration(SourceBuilder code) {
      addGetterAnnotations(code);
      code.add("private %s %s = null;\n", property.getType(), property.getName());
    }

    @Override
    public void addBuilderFieldAccessors(SourceBuilder code, final Metadata metadata) {
      // Setter
      code.addLine("")
          .addLine("/**")
          .addLine(" * Sets the value to be returned by %s.",
              metadata.getType().javadocNoArgMethodLink(property.getGetterName()))
          .addLine(" *")
          .addLine(" * @return this {@code %s} object", metadata.getBuilder().getSimpleName())
          .addLine(" */");
      addAccessorAnnotations(code);
      code.add("public %s %s(", metadata.getBuilder(), setter(property));
      addGetterAnnotations(code);
      code.add("%s %s) {\n", property.getType(), property.getName())
          .addLine("  this.%1$s = %1$s;", property.getName())
          .addLine("  return (%s) this;", metadata.getBuilder())
          .addLine("}");

      // Getter
      code.addLine("")
          .addLine("/**")
          .addLine(" * Returns the value that will be returned by %s.",
              metadata.getType().javadocNoArgMethodLink(property.getGetterName()))
          .addLine(" */");
      addGetterAnnotations(code);
      code.addLine("public %s %s() {", property.getType(), getter(property))
          .addLine("  return %s;", property.getName())
          .addLine("}");
    }

    @Override
    public void addValueFieldDeclaration(SourceBuilder code, String finalField) {
      addGetterAnnotations(code);
      code.add("private final %s %s;\n", property.getType(), finalField);
    }

    @Override
    public void addFinalFieldAssignment(SourceBuilder code, String finalField, String builder) {
      code.addLine("%s = %s.%s;", finalField, builder, property.getName());
    }

    @Override
    public void addMergeFromValue(SourceBuilder code, String value) {
      code.addLine("%s(%s.%s());", setter(property), value, property.getGetterName());
    }

    @Override
    public void addMergeFromBuilder(SourceBuilder code, Metadata metadata, String builder) {
      code.addLine("%s(%s.%s());", setter(property), builder, property.getGetterName());
    }

    @Override
    public void addGetterAnnotations(SourceBuilder code) {
      for (TypeElement nullableAnnotation : nullables) {
        code.add("@%s ", nullableAnnotation);
      }
    }

    @Override
    public void addSetFromResult(SourceBuilder code, String builder, String variable) {
      code.addLine("%s.%s(%s);", builder, setter(property), variable);
    }

    @Override
    public boolean isTemplateRequiredInClear() {
      return true;
    }

    @Override
    public void addClear(SourceBuilder code, String template) {
      code.addLine("%1$s = %2$s.%1$s;", property.getName(), template);
    }

    @Override
    public void addPartialClear(SourceBuilder code) {
      if (true) {
        code.addLine("%s = null;", property.getName());
      }
    }
  }
}
