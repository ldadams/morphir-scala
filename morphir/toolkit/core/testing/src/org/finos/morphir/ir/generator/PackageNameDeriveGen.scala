package org.finos.morphir.ir.generator

import org.finos.morphir.ir.packages.PackageName
import zio.test.magnolia.DeriveGen
import zio.test.magnolia.DeriveGen._

object PackageNameDeriveGen extends PackageNameDeriveGen
trait PackageNameDeriveGen {
  implicit val packageNameDeriveGen: DeriveGen[PackageName] = DeriveGen.instance(PackageNameGen.packageName)
}
