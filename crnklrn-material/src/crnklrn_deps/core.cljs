(ns crnklrn-deps.core
  (:require
    ["react-dom" :refer [render]]
    ["react" :refer [useState useCallback ]]
    [crnklrn-deps.hiccup :refer [html]]
    ["clsx" :as clsx]
    ["@material-ui/styles" :refer [ThemeProvider]]
    ["@material-ui/core/styles" :refer [makeStyles createMuiTheme responsiveFontSizes]]
    ["@material-ui/core/CssBaseline" :default CssBaseline]
    ["@material-ui/core/AppBar" :default AppBar]
    ["@material-ui/core/Toolbar" :default Toolbar]
    ["@material-ui/core/List" :default List]
    ["@material-ui/core/Typography" :default Typography]
    ["@material-ui/core/Divider" :default Divider]
    ["@material-ui/core/IconButton" :default IconButton]
    ["@material-ui/core/Badge" :default Badge]
    ["@material-ui/core/Container" :default Container]
    ["@material-ui/core/Grid" :default Grid]
    ["@material-ui/core/Paper" :default Paper]
    ["@material-ui/core/Link" :default Link]
    ["@material-ui/icons/Menu" :default MenuIcon]
    ["@material-ui/icons/ChevronLeft" :default ChevronLeftIcon]
    ["@material-ui/icons/Notifications" :default NotificationsIcon]
    ["@material-ui/core/Button" :default Button]
    [goog.object :as gobj]))


(enable-console-print!)
(def drawerWidth 240)
(def theme (responsiveFontSizes (createMuiTheme)))
(def useStyles
  (makeStyles
    (fn [theme]
      #js {:root             #js {:display "flex"}
           :toolbar          #js {:paddingRight 24}         ; keep right padding when drawer closed
           :toolbarIcon      (gobj/extend
                               #js {:display        "flex"
                                    :alignItems     "center"
                                    :justifyContent "flex-end"
                                    :padding        "0 8px"}
                               (gobj/getValueByKeys
                                 theme "mixins" "toolbar"))
           :appBar           #js {:zIndex (+ (gobj/getValueByKeys theme "zIndex" "drawer") 1)
                                  :transition
                                          ((gobj/getValueByKeys theme "transitions" "create")
                                           #js ["width" "margin"]
                                           #js {:easing   (gobj/getValueByKeys theme "transitions" "easing" "sharp")
                                                :duration (gobj/getValueByKeys theme "transitions" "duration" "leavingScreen")})}
           :appBarShift      #js {:marginLeft drawerWidth
                                  :width      (str "calc(100% - " drawerWidth "px")
                                  :transition
                                              ((gobj/getValueByKeys theme "transitions" "create")
                                               #js ["width" "margin"]
                                               #js {:easing   (gobj/getValueByKeys theme "transitions" "easing" "sharp")
                                                    :duration (gobj/getValueByKeys theme "transitions" "duration" "enteringScreen")})}
           :menuButton       #js {:marginRight 36}
           :menuButtonHidden #js {:display "none"}
           :title            #js {:flexGrow 1}
           :drawerPaper      #js {:position   "relative"
                                  :whiteSpace "nowrap"
                                  :width      drawerWidth
                                  :transition ((gobj/getValueByKeys theme "transitions" "create")
                                               "width"
                                               #js {:easing   (gobj/getValueByKeys theme "transitions" "easing" "sharp")
                                                    :duration (gobj/getValueByKeys theme "transitions" "duration" "enteringScreen")})}
           :drawerPaperClose (gobj/set
                               #js {:overflowX "hidden"
                                    :transition
                                               ((gobj/getValueByKeys theme "transitions" "create")
                                                #js ["width" "margin"]
                                                #js {:easing   (gobj/getValueByKeys theme "transitions" "easing" "sharp")
                                                     :duration (gobj/getValueByKeys theme "transitions" "duration" "leavingScreen")})
                                    :width     ((gobj/getValueByKeys theme "spacing") 7)
                                    }
                               #js [((gobj/getValueByKeys
                                       theme
                                       "breakpoints"
                                       "up")
                                     "sm")]
                               #js {:width ((gobj/getValueByKeys theme "spacing") 9)})
           :appBarSpacer     (gobj/getValueByKeys theme "mixins" "toolbar")
           :content          #js {:flexGrow 1
                                  :height   "100vh:"
                                  :overflow "auto"}
           :container        #js {:paddingTop    ((gobj/getValueByKeys theme "spacing") 4)
                                  :paddingBottom ((gobj/getValueByKeys theme "spacing") 4)}
           :paper            #js {:padding       ((gobj/getValueByKeys theme "spacing") 2)
                                  :display       "flex"
                                  :overflow      "auto"
                                  :flexDirection "column"}
           :fixedHeight      #js {:height 240}})))

;; define your app data so that it doesn't get over-written on reload


(defn Dashboard [props]
  (prn :hi)
  (let [classes          (useStyles)
        [open, setOpen] (useState true)
        handleDrawerOpen (useCallback (fn [e] (setOpen true)))]
    (js/console.log theme)
    (html
      [:div {:className ^:inline (gobj/get classes "root")}
       [CssBaseline {}]
       [:> AppBar {:position  "absolute"
                :className (clsx (gobj/get classes "root")
                             (and open (gobj/get classes "appBarShift")))}
        [:> Toolbar {:className (gobj/get classes "toolbar")}
         #_(html [Button {:variant "contained" :color "primary"} "hello world"])
         [:> IconButton
          {:edge       "start"
           :color      "inherit"
           :aria-label "Open drawer"
           :onClick    handleDrawerOpen
           :className  (clsx
                         (gobj/get classes "menuButton")
                         (and open
                           (gobj/get classes "menuButtonHidden")))}
          [:> MenuIcon {}]]
         [:> Typography
          {:component "h1"
           :variant   "h6"
           :color     "inherit"
           ;:noWrap "noWrap"
           :className (gobj/get classes "title")}
          "Component"]
         [:> IconButton
          {:color "inherit"}
          [:> Badge {:badgeContent 4
                     :color        "secondary"}
           [:> NotificationsIcon {}]]]
         ]]
       #_[Button {:variant "contained" :color "primary"} "hello world"]
       ])))

(defn start []
  (render
    (html
      [:> ThemeProvider
       {:theme theme}
       [:> Dashboard {}]])
    (.. js/document (getElementById "app"))))

(defn ^:export init []
  ;; init is called ONCE when the page loads
  ;; this is called in the index.html and must be exported
  ;; so it is available even in :advanced release builds
  (prn "your momma an dem!")

  (start))

(defn stop []
  ;; stop is called before any code is reloaded
  ;; this is controlled by :before-load in the config
  (js/console.log "stop"))
