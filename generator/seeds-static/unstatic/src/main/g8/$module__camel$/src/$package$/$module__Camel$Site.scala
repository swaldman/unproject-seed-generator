package $package$

import scala.collection.*

import unstatic.*
import unstatic.ztapir.*
import unstatic.ztapir.simple.*

import unstatic.*, UrlPath.*

import java.nio.file.Path as JPath

import untemplate.Untemplate.AnyUntemplate

object $module;format="Camel"$Site extends ZTSite.SingleRootComposite( JPath.of("$module;format="camel"$/static") ):

  // edit this to where your site will actually be served!
  override val serverUrl : Abs    = Abs("https://www.example.com/")
  override val basePath  : Rooted = Rooted.root

  // customize this to what the layout you want requires!
  case class MainLayoutInput( renderLocation : SiteLocation, mbTitle : Option[String], mainContentHtml : String )

  // get rid of this -- modify it into something useful and/or include something like a SimpleBlog defined as a singleton object
  object HelloWorldPage extends ZTEndpointBinding.Source:
    val location = $module;format="Camel"$Site.location("/index.html")
    val task = zio.ZIO.attempt {
      layout_main_html( MainLayoutInput( location, Some("Hello"),  "<h1>Hello World!</h1>" ) ).text
    }
    val endpointBindings = ZTEndpointBinding.publicReadOnlyHtml( location, task, None, immutable.Set("hello-world") ) :: Nil
  end HelloWorldPage

  // avoid conflicts, but early items in the lists take precedence over later items
  override val endpointBindingSources : immutable.Seq[ZTEndpointBinding.Source] = immutable.Seq( HelloWorldPage )

object $module;format="Camel"$SiteGenerator extends ZTMain($module;format="Camel"$Site, "$module;format="snake"$-site")

