package org.finos.morphir.ir.packages
import org.finos.morphir.ir.module.{ModuleName, Specification => ModuleSpec}
import org.finos.morphir.ir.Value.{Specification => ValueSpec}
import org.finos.morphir.ir.{Name, Path}

final case class Specification[+TA](modules: Map[ModuleName, ModuleSpec[TA]]) {
  self =>

  def eraseAttributes: Specification[scala.Unit] = self.mapAttributes(_ => ())

  def lookupModuleSpecification(path: Path): Option[ModuleSpec[TA]] =
    lookupModuleSpecification(ModuleName.fromPath(path))

  def lookupModuleSpecification(moduleName: ModuleName): Option[ModuleSpec[TA]] =
    modules.get(moduleName)

  def lookupTypeSpecification(path: Path, name: Name): Option[ModuleSpec[TA]] =
    lookupTypeSpecification(ModuleName(path, name))

  def lookupTypeSpecification(moduleName: ModuleName): Option[ModuleSpec[TA]] =
    modules.get(moduleName)

  def mapAttributes[TB](func: TA => TB): Specification[TB] = Specification(modules.map { case (name, moduleSpec) =>
    (name, moduleSpec.mapAttributes(func))
  })

  def lookupValueSpecification(
      modulePath: Path,
      localName: Name
  ): Option[ValueSpec[TA]] =
    lookupModuleSpecification(modulePath).flatMap(_.lookupValueSpecification(localName))

}

object Specification {
  val empty: Specification[Nothing] = Specification(Map.empty)

  type Raw = Specification[Unit]
  object Raw {
    def apply(modules: Map[ModuleName, ModuleSpec[Unit]]): Raw =
      Specification(modules)
  }
}
