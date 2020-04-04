/**
 * Copyright (c) Facebook, Inc. and its affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

#import "AppDelegate.h"

#import <React/RCTBridge.h>
#import <React/RCTBundleURLProvider.h>
#import <React/RCTRootView.h>
#import "RNJSLoader.h"
#import "ReactViewController.h"

@implementation AppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
#if DEBUG
  [[NSNotificationCenter defaultCenter] addObserver:self
                                           selector:@selector(javaScriptDidLoad:)
                                               name:RCTJavaScriptDidLoadNotification
                                             object:nil];
  NSURL *main = [[NSBundle mainBundle] URLForResource:@"common" withExtension:@"jsbundle"];
  RCTBridge *bridge = [[RCTBridge alloc] initWithBundleURL:main moduleProvider:nil launchOptions:launchOptions];
  [[RNJSLoader sharedInstance] setupBridge:bridge];
  self.window = [[UIWindow alloc] initWithFrame:[UIScreen mainScreen].bounds];
  self.window.rootViewController = [self loadingViewController];
  [self.window makeKeyAndVisible];
  return YES;
#else
  RCTBridge *bridge = [[RCTBridge alloc] initWithDelegate:self launchOptions:launchOptions];
  RCTRootView *rootView = [[RCTRootView alloc] initWithBridge:bridge
                                                   moduleName:@"rewards"
                                            initialProperties:nil];

  rootView.backgroundColor = [[UIColor alloc] initWithRed:1.0f green:1.0f blue:1.0f alpha:1];

  self.window = [[UIWindow alloc] initWithFrame:[UIScreen mainScreen].bounds];
  UIViewController *rootViewController = [UIViewController new];
  rootViewController.view = rootView;
  self.window.rootViewController = rootViewController;
  [self.window makeKeyAndVisible];
  return YES;
#endif
}

- (NSURL *)sourceURLForBridge:(RCTBridge *)bridge {
#if DEBUG
  return [[RCTBundleURLProvider sharedSettings] jsBundleURLForBundleRoot:@"index" fallbackResource:nil];
#else
  return [[NSBundle mainBundle] URLForResource:@"main" withExtension:@"jsbundle"];
#endif
}

- (void)javaScriptDidLoad:(NSNotification *)notification {
  [[NSNotificationCenter defaultCenter] removeObserver:self];
  NSURL *main = [[NSBundle mainBundle] URLForResource:@"main_app" withExtension:@"jsbundle"];
  ReactViewController *rootVC = [[ReactViewController alloc] initWithModuleName:@"split_jsbundle" url:main];
  self.rootNavigationController = [[UINavigationController alloc] initWithRootViewController:rootVC];
  [self.rootNavigationController setNavigationBarHidden:YES animated:NO];
  self.window.rootViewController = self.rootNavigationController;
}

- (UIViewController *)loadingViewController {
  UIViewController *vc = [[UIViewController alloc] init];
  vc.view.backgroundColor = [UIColor whiteColor];
  return vc;
}

@end
