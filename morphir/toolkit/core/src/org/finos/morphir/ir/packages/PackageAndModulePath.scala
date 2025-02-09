package org.finos.morphir.ir.packages
import org.finos.morphir.ir.{FQName, ModulePath, Name}

final case class PackageAndModulePath(packageName: PackageName, modulePath: ModulePath) {
  self =>
  def %(name: Name): FQName = FQName(packageName, modulePath, name)

  def %(name: String): FQName = FQName(packageName, modulePath, Name.fromString(name))
}
